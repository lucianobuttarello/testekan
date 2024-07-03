package com.example.testekan.repository.impl;

import com.example.testekan.repository.entity.Beneficiario;
import com.example.testekan.repository.entity.Documento;
import com.example.testekan.repository.jpa.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoImpl {

    @Autowired
    private DocumentoRepository repository;

    public Documento obterDocumentoPorIdBeneficiarioEDescricao(Long id,String descricao) {
             return repository.findByBeneficiarioIdAndDescricao(id,descricao);
    }

    public void deletarDocumento(Documento documento) {
        repository.delete(documento);
    }

    public Optional<Documento> obterDocumentoPorCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }
}
