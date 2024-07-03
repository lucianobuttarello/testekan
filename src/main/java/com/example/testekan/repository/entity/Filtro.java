package com.example.testekan.repository.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Filtro {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String nome;
    private Integer pagina;
    private Integer tamanho;
}
