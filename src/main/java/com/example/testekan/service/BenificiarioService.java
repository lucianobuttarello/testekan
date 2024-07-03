package com.example.testekan.service;

import static com.example.testekan.service.factory.BeneficiarioFactory.constroiBeneficiario;
import static com.example.testekan.service.factory.BeneficiarioFactory.constroiBeneficiarioResponse;
import static com.example.testekan.service.factory.BeneficiarioFactory.constroiListaBeneficiariosResponse;
import static com.example.testekan.service.factory.BeneficiarioFactory.constroiBeneficiarioAtualizacao;
import static com.example.testekan.service.factory.BeneficiarioFactory.constroiDocumento;
import static com.example.testekan.service.factory.BeneficiarioFactory.constroiDocumentoAtualizado;
import static com.example.testekan.service.factory.BeneficiarioFactory.constroiDocumentoExistente;
import com.example.testekan.dominio.BeneficiarioResponse;
import com.example.testekan.dominio.BeneficiarioPayload;
import com.example.testekan.dominio.DocumentoPayload;
import com.example.testekan.repository.entity.Beneficiario;
import com.example.testekan.repository.entity.Documento;
import com.example.testekan.repository.entity.Filtro;
import com.example.testekan.repository.impl.BeneficiarioImpl;
import com.example.testekan.repository.impl.DocumentoImpl;

import static com.example.testekan.util.ValidaDados.validaPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BenificiarioService {

    @Autowired
    private BeneficiarioImpl beneficiarioImpl;
    @Autowired
    private DocumentoImpl documentoImpl;

    public BeneficiarioResponse cadastroBeneficiario(BeneficiarioPayload payload) {
        Beneficiario documento = constroiBeneficiario(payload);

        Set<String> descricoes = payload.getDocumentos().stream().map(DocumentoPayload::getDescricao).collect(Collectors.toSet());
        if (descricoes.size() < payload.getDocumentos().size()) {
            throw new RuntimeException("Existem documentos duplicados com a mesma descrição.");
        }

        validaPayload(payload);
        validaDocumentoExistenteInclusao(payload);

        documento = beneficiarioImpl.salvar(documento);
        BeneficiarioResponse resposta =  constroiBeneficiarioResponse(documento);
        return resposta;
    }

    private void validaDocumentoExistenteInclusao(BeneficiarioPayload payload) {
        payload.getDocumentos().forEach(item -> {
            Documento documento = documentoImpl.obterDocumentoPorCodigo(item.getCodigo()).orElse(null);
            if (!Objects.isNull(documento)) {
                if (documento.getCodigo().equals(item.getCodigo())) {
                    throw new RuntimeException("Documento Informado já pertence a outro Beneficiario.");
                }
            };
        });
    }

    private void validaDocumentoExistenteAlteracao(BeneficiarioPayload payload) {
        payload.getDocumentos().forEach(item -> {
            Documento documento = documentoImpl.obterDocumentoPorCodigo(item.getCodigo()).orElse(null);
            if (!Objects.isNull(documento)) {
                if (!documento.getCodigo().equals(item.getCodigo())) {
                    throw new RuntimeException("Documento Informado já pertence a outro Beneficiario.");
                }
            };
        });
    }

    public List<BeneficiarioResponse> consultaBeneficiario() {
        List<Beneficiario> resposta = beneficiarioImpl.listarTodosBeneficiarios();

        if (resposta.isEmpty()) {
            throw new RuntimeException("Nenhum Beneficiario encontrado");
        }

        return constroiListaBeneficiariosResponse(resposta);
    }

    public Page<BeneficiarioResponse> consultaBeneficiarioPorFiltro(Filtro filtro) {
        Pageable pageable = PageRequest.of(filtro.getPagina(), filtro.getTamanho());
        Page<Beneficiario> resposta = beneficiarioImpl.listarPorFiltro(filtro.getDataInicio(),filtro.getDataFim(),filtro.getNome(),pageable);

        if (resposta.isEmpty()) {
            throw new RuntimeException("Nenhum Beneficiario encontrado");
        }

        List<BeneficiarioResponse> lista = constroiListaBeneficiariosResponse(resposta.getContent());

        return new PageImpl<>(lista, pageable, resposta.getTotalElements());
    }

    public void deletaBeneficiario(Long id) {
        Beneficiario beneficiario = beneficiarioImpl.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Beneficiario não encontrado"));

        beneficiarioImpl.deletarPorId(beneficiario.getId());
    }

    public BeneficiarioResponse atualizaBeneficiario(BeneficiarioPayload payload, Long id) {
        Beneficiario beneficiarioResposta = beneficiarioImpl.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Beneficiario não encontrado"));

        validaPayload(payload);
        validaDocumentoExistenteAlteracao(payload);

        Beneficiario beneficiario = constroiBeneficiarioAtualizacao(payload, id,beneficiarioResposta);

        List<Documento> listaDocumentos = new ArrayList<>();

        payload.getDocumentos().forEach(item -> {
            Documento documentoReposta = documentoImpl.obterDocumentoPorIdBeneficiarioEDescricao(beneficiarioResposta.getId(), item.getDescricao());
            if (Objects.isNull(documentoReposta)) {
                listaDocumentos.add(constroiDocumento(item, beneficiario));
            } else {
                if (documentoReposta.getCodigo().equals(item.getCodigo())) {
                    listaDocumentos.add(constroiDocumentoExistente(item, documentoReposta, beneficiario));
                } else {
                    listaDocumentos.add(constroiDocumentoAtualizado(item, documentoReposta, beneficiario));
                }
            }
        });

        beneficiario.setDocumentos(listaDocumentos);

        beneficiarioResposta.getDocumentos().forEach(item -> {
            documentoImpl.deletarDocumento(item);
        });

        beneficiarioImpl.salvar(beneficiario);

        return constroiBeneficiarioResponse(beneficiario);
    }
}
