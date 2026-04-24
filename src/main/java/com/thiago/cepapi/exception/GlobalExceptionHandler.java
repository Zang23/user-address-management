package com.thiago.cepapi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<String> handleCep(CepInvalidoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(CepFormatoInvalidoException.class)
    public ResponseEntity<String> handleCepFormato(CepFormatoInvalidoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handleNotFound(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicidadeException.class)
    public ResponseEntity<String> handleDuplicidade(DuplicidadeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeral(Exception ex) {
        return ResponseEntity.status(500).body("Erro interno do servidor");
    }
}
