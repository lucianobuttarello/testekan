package com.example.testekan.service.factory;

import com.example.testekan.dominio.BeneficiarioPayload;
import com.example.testekan.dominio.BeneficiarioResponse;
import com.example.testekan.dominio.DocumentoPayload;
import com.example.testekan.dominio.DocumentoResponse;
import com.example.testekan.repository.entity.Beneficiario;
import com.example.testekan.repository.entity.Documento;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeneficiarioFactory {

    public static Beneficiario constroiBeneficiario (BeneficiarioPayload payload){
        Beneficiario beneficiario =  Beneficiario.builder()
                .nome(payload.getNome())
                .dataNascimento(payload.getDataNascimento())
                .telefone(payload.getTelefone())
                .dataInclusao(LocalDate.now())
                .build();

        beneficiario.setDocumentos(constroiDocumentos(payload.getDocumentos(),beneficiario));

        return beneficiario;
    }

    public static List<Documento> constroiDocumentos (List<DocumentoPayload> payload, Beneficiario beneficiario){
        List<Documento> lista = new ArrayList<>();
        payload.forEach(item -> {
            lista.add(constroiDocumento(item, beneficiario));
        });
        return lista;
    }

    public static Documento constroiDocumento(DocumentoPayload payload,Beneficiario beneficiario){
            return Documento.builder().codigo(payload.getCodigo())
                    .descricao(payload.getDescricao())
                    .dataInclusao(LocalDate.now())
                    .beneficiario(beneficiario)
                    .build();
    }

    public static Documento constroiDocumentoAtualizado(DocumentoPayload payload,Documento documento,Beneficiario beneficiario){
        return Documento.builder()
                .beneficiario(beneficiario)
                .id(documento.getId())
                .codigo(payload.getCodigo())
                .descricao(payload.getDescricao())
                .dataInclusao(documento.getDataInclusao())
                .dataAlteracao(LocalDate.now())
                .build();
    }

    public static Documento constroiDocumentoExistente(DocumentoPayload payload,Documento documento,Beneficiario beneficiario){
        return Documento.builder()
                .beneficiario(beneficiario)
                .id(documento.getId())
                .codigo(documento.getCodigo())
                .descricao(payload.getDescricao())
                .dataAlteracao(LocalDate.now())
                .dataInclusao(documento.getDataInclusao())
                .build();
    }

    public static BeneficiarioResponse constroiBeneficiarioResponse(Beneficiario payload) {
        return BeneficiarioResponse.builder()
                .idBeneficiario(payload.getId())
                .nome(payload.getNome())
                .dataInclusao(payload.getDataInclusao())
                .dataAlteracao(payload.getDataAlteracao())
                .telefone(payload.getTelefone())
                .dataNascimento(payload.getDataNascimento())
                .documentos(constroiDocumentoResponse(payload.getDocumentos()))
                .build();
    }

    private static List<DocumentoResponse> constroiDocumentoResponse(List<Documento> documentos) {
        List<DocumentoResponse> lista = new ArrayList<>();
        documentos.forEach(item -> {
            lista.add(DocumentoResponse.builder()
                    .idDocumento(item.getId())
                    .descricao(item.getDescricao())
                    .dataAtualizacao(item.getDataAlteracao())
                    .dataInclusao(item.getDataInclusao())
                    .codigo(item.getCodigo())
                    .build());
        });
        return lista;
    }

    public static List<BeneficiarioResponse> constroiListaBeneficiariosResponse(List<Beneficiario> payload) {
        List<BeneficiarioResponse> lista = new ArrayList<>();
        payload.forEach(item -> {
            lista.add(constroiBeneficiarioResponse(item));
        });
        return lista;
    }

    public static Beneficiario constroiBeneficiarioAtualizacao (BeneficiarioPayload payload, Long id, Beneficiario beneficiarioAtual){
        Beneficiario beneficiario = Beneficiario.builder()
                .id(id)
                .nome(payload.getNome())
                .dataNascimento(payload.getDataNascimento())
                .telefone(payload.getTelefone())
                .dataInclusao(beneficiarioAtual.getDataInclusao())
                .dataAlteracao(LocalDate.now())
                .build();
        return beneficiario;
    }
}
