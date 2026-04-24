package com.thiago.cepapi.exception;

public class CepInvalidoException extends RuntimeException{
    
    public CepInvalidoException(){
        super("CEP invalido");
    }

}
