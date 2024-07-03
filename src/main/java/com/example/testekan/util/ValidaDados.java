package com.example.testekan.util;

import com.example.testekan.dominio.BeneficiarioPayload;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidaDados {


    public static void validaPayload(BeneficiarioPayload payload) {
        if (payload.getNome().isEmpty()) {
            throw new RuntimeException("Nome Vazio: ");
        }
        if (payload.getTelefone().isEmpty() || payload.getTelefone().length() < 11) {
            throw new RuntimeException("Telefone Celular Invalido: ");
        }
        if (payload.getDataNascimento().isAfter(LocalDate.now()) || Objects.isNull(payload.getDataNascimento())) {
            throw new RuntimeException("Data de Nascimento não pode ser maior que hoje: ");
        }
        if (payload.getDocumentos().size() <=0) {
            throw new RuntimeException("É necessario informar um documento");
        }

        payload.getDocumentos().forEach(item -> {
            if (item.getCodigo().isEmpty()) {
                throw new RuntimeException("É necessario informar um codigo");
            }
            if (item.getDescricao().isEmpty()) {
                throw new RuntimeException("É necessario informar uma descrição");
            }
            if (item.getDescricao().equals(Constants.CPF)) {
                validaCPF(item.getCodigo());
            }
        });
    }

    public static void validaCPF(String codigo) {
        if (Boolean.FALSE.equals(isValid(codigo))) {
            throw new RuntimeException("Cpf Invalido: " + codigo);
        }

    }
    private static boolean isValid(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11)
            return false;

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}"))
            return false;

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstDigit = (sum * 10) % 11;
        if (firstDigit == 10) firstDigit = 0;

        // Verifica o primeiro dígito verificador
        if (Character.getNumericValue(cpf.charAt(9)) != firstDigit)
            return false;

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondDigit = (sum * 10) % 11;
        if (secondDigit == 10) secondDigit = 0;

        // Verifica o segundo dígito verificador
        if (Character.getNumericValue(cpf.charAt(10)) != secondDigit)
            return false;

        return true;
    }
}