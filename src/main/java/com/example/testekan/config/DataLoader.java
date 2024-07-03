package com.example.testekan.config;

import com.example.testekan.repository.entity.Usuario;
import com.example.testekan.repository.impl.UsuarioImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    @Autowired
    private UsuarioImpl usuarioImpl;

    @PostConstruct
    public void loadData() {
        Usuario admin = new Usuario();
        admin.setLogin("admin");
        admin.setPassword("$2a$12$5s2Ramxj.9Vk9uQabdVireIQ7DblzZRB/EIvut27Bg/xu2fDgf8cy");
        usuarioImpl.salvar(admin);
    }
}