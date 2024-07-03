package com.example.testekan.repository.impl;

import com.example.testekan.repository.entity.Beneficiario;
import com.example.testekan.repository.entity.Usuario;
import com.example.testekan.repository.jpa.BeneficiarioRepository;
import com.example.testekan.repository.jpa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioImpl {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario findByLogin(String username) {
        return  repository.findByLogin(username);
    }
}
