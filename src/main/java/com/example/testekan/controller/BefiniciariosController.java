package com.example.testekan.controller;

import com.example.testekan.api.BeneficiarioApi;
import com.example.testekan.dominio.BeneficiarioResponse;
import com.example.testekan.dominio.BeneficiarioPayload;
import com.example.testekan.repository.entity.Filtro;
import com.example.testekan.security.TokenService;
import com.example.testekan.security.dto.Login;
import com.example.testekan.repository.entity.Usuario;
import com.example.testekan.service.BenificiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class BefiniciariosController implements BeneficiarioApi {

    private final BenificiarioService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    public BefiniciariosController(BenificiarioService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<BeneficiarioResponse> cadastroBeneficiario(@RequestBody BeneficiarioPayload payload) {
        if (Objects.isNull(payload)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.cadastroBeneficiario(payload));
    }

    @Override
    @PostMapping("/filtro")
    public ResponseEntity<Page<BeneficiarioResponse>> consultaBeneficiarioPorFiltro(@RequestBody Filtro filtro) {
        return ResponseEntity.ok(service.consultaBeneficiarioPorFiltro(filtro));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<BeneficiarioResponse>> consultaBeneficiario() {
        return ResponseEntity.ok(service.consultaBeneficiario());
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaBeneficiario(@PathVariable Long id) {
        service.deletaBeneficiario(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BeneficiarioResponse> atualizaBeneficiario(@PathVariable Long id, @RequestBody BeneficiarioPayload payload) {
        if (Objects.isNull(payload)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(service.atualizaBeneficiario(payload,id));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.login(),login.senha());
        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var usuario = (Usuario)authentication.getPrincipal();

        return ResponseEntity.ok(tokenService.gerarToken(usuario));
    }
}
