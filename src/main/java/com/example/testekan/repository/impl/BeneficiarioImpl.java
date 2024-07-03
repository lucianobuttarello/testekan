package com.example.testekan.repository.impl;

import com.example.testekan.repository.entity.Beneficiario;
import com.example.testekan.repository.jpa.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioImpl {

    @Autowired
    private BeneficiarioRepository repository;

    public Page<Beneficiario> listarPorFiltro(LocalDate dataInicio, LocalDate dataFim,String nome, Pageable pageable) {
        return repository.findByDataInclusaoBetween(dataInicio,dataFim,nome,pageable);
    }

    public Beneficiario salvar(Beneficiario beneficiario) {
        return repository.save(beneficiario);
    }

    public Optional<Beneficiario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);
    }

    public List<Beneficiario> listarTodosBeneficiarios() {
        return repository.findAll();
    }
}
