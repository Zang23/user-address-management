package com.thiago.cepapi.exception;

public class CepFormatoInvalidoException extends RuntimeException {
    public CepFormatoInvalidoException() {
        super("Formato de CEP inválido");
    }
}
