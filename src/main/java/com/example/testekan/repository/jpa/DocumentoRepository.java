package com.example.testekan.repository.jpa;

import com.example.testekan.repository.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    Documento findByBeneficiarioIdAndDescricao(Long id,String descricao);

    Optional<Documento> findByCodigo(String codigo);
}
