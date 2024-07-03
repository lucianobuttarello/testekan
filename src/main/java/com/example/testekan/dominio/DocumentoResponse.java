package com.example.testekan.dominio;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DocumentoResponse {

    private Long idDocumento;
    private String descricao;
    private LocalDate dataInclusao;
    private LocalDate dataAtualizacao;
    private String codigo;
}
