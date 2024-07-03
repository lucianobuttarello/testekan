package com.example.testekan.dominio;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class BeneficiarioResponse {

    private Long idBeneficiario;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;
    private List<DocumentoResponse> documentos;
}
