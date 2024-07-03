package com.example.testekan.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.testekan.repository.entity.Usuario;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {
    public String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("")
                .withSubject(usuario.getLogin())
                .withClaim("id",usuario.getId())
                .withExpiresAt(LocalDateTime.now().plusMinutes(20).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("secreta"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secreta")).build().verify(token).getSubject();
    }
}
