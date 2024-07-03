package com.example.testekan.repository.jpa;

import com.example.testekan.repository.entity.Beneficiario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
    @Query("SELECT b FROM Beneficiario  b "
            + "WHERE (:dataInicio IS NULL OR :dataFim IS NULL OR b.dataInclusao BETWEEN :dataInicio AND :dataFim) "
            + "AND (:nome IS NULL OR LOWER(b.nome) LIKE LOWER(CONCAT(:nome, '%')))")
    Page<Beneficiario> findByDataInclusaoBetween(LocalDate dataInicio, LocalDate dataFim, String nome, Pageable pageable);}
