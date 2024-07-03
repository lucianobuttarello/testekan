package com.example.testekan.dominio;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class BeneficiarioPayload {

    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private List<DocumentoPayload> documentos;
}
