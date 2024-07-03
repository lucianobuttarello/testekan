package com.example.testekan.security;

import com.example.testekan.repository.entity.Usuario;
import com.example.testekan.repository.impl.UsuarioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioImpl usuarioimpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioimpl.findByLogin(username);
        return usuarioimpl.findByLogin(username);
    }
}
