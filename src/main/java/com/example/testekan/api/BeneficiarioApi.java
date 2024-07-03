package com.example.testekan.api;

import com.example.testekan.dominio.BeneficiarioPayload;
import com.example.testekan.dominio.BeneficiarioResponse;
import com.example.testekan.exception.ErrorDTO;
import com.example.testekan.repository.entity.Filtro;
import com.example.testekan.repository.entity.Usuario;
import com.example.testekan.security.dto.Login;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BeneficiarioApi {
    @Operation(summary = "Cadastro de Beneficiarios", tags = "Beneficiarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BeneficiarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDTO.class))) })
    ResponseEntity<BeneficiarioResponse> cadastroBeneficiario(@RequestBody BeneficiarioPayload payload);

    @Operation(summary = "Consulta de Beneficiarios Por Filtro", tags = "Beneficiarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BeneficiarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDTO.class))) })
    ResponseEntity<Page<BeneficiarioResponse>> consultaBeneficiarioPorFiltro(Filtro filtro);

    @Operation(summary = "Consulta de Beneficiarios", tags = "Beneficiarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BeneficiarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDTO.class))) })
    @PostMapping
    ResponseEntity<List<BeneficiarioResponse>> consultaBeneficiario();

    @Operation(summary = "Remoção de Beneficiarios", tags = "Beneficiarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BeneficiarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDTO.class))) })
    @DeleteMapping
    ResponseEntity<Void> deletaBeneficiario(@PathVariable Long id);

    @Operation(summary = "Atualização de Beneficiarios", tags = "Beneficiarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = BeneficiarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDTO.class))) })
    @PutMapping("/{id}")
    ResponseEntity<BeneficiarioResponse> atualizaBeneficiario(@PathVariable Long id, @RequestBody BeneficiarioPayload payload);

    @Operation(summary = "Realiza login para obter o Token", tags = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDTO.class))) })
    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody Login login);
}
