package com.example.testekan.repository.jpa;

import com.example.testekan.repository.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String nome);
}
