package com.gerenciamento.financas.utils;

import java.util.Arrays;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import com.gerenciamento.financas.exception.GerenciamentoException;
import java.util.List;

public class Validadors {
    public String validacaoCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            throw new GerenciamentoException("O CNPJ deve conter 14 digitos númericos", HttpStatus.BAD_REQUEST);
        }

        final List<Integer> pesoDigitoUm = Arrays.asList(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);

        Integer digitoUm = calcularDigitoCnpj(cnpj.substring(0, 12), pesoDigitoUm);

        final List<Integer> pesoDigitoDois = Arrays.asList(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);

        Integer digitoDois = calcularDigitoCnpj(cnpj.substring(0, 12) + digitoUm, pesoDigitoDois);

        if (ObjectUtils.notEqual(cnpj, cnpj.substring(0, 12) + digitoUm + digitoDois)) {
            throw new GerenciamentoException("O CNPJ está inválido", HttpStatus.BAD_REQUEST);
        }

        return cnpj;
    }

    public String validacaoCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            throw new GerenciamentoException("O CPF deve conter 11 digitos númericos", HttpStatus.BAD_REQUEST);
        }

        Integer digitoUm = calcularDigitoCpf(cpf.substring(0, 9), 10);

        Integer digitoDois = calcularDigitoCpf(cpf.substring(0, 9) + digitoUm, 11);

        if (ObjectUtils.notEqual(cpf, cpf.substring(0, 9) + digitoUm + digitoDois)) {
            throw new GerenciamentoException("O CPF está inválido", HttpStatus.BAD_REQUEST);
        }

        return cpf;
    }

    private static Integer calcularDigitoCnpj(String cnpj, List<Integer> peso) {
        Integer somatorio = 0;

        for (int i = cnpj.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(cnpj.charAt(i));
            somatorio += digito * peso.get(peso.size() - cnpj.length() + i);
        }

        somatorio = 11 - somatorio % 11;

        return somatorio > 9 ? 0 : somatorio;
    }

    private static Integer calcularDigitoCpf(String cpf, Integer peso) {
        Integer somatorio = 0;
        Integer tamanho = peso;

        for (int i = 0; i < cpf.length(); i++) {
            somatorio += Character.getNumericValue(cpf.charAt(i)) * tamanho--;
        }

        Integer restante = somatorio % 11;

        return (restante < 2) ? 0 : (11 - restante);
    }
}
