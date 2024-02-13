package com.gerenciamento.financas.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class GerenciamentoException extends RuntimeException{

    private final String message;

    private final HttpStatus httpStatus;

    public GerenciamentoException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
