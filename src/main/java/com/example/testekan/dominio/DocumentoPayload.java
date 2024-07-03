package com.example.testekan.dominio;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DocumentoPayload {

    private String descricao;
    private String codigo;
}
