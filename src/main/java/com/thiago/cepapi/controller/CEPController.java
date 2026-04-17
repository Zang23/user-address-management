package com.thiago.cepapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiago.cepapi.dto.response.EnderecoResponseDto;
import com.thiago.cepapi.service.CEPService;

@RestController
@RequestMapping("/cep")
public class CEPController {

    private final CEPService service;

    public CEPController(CEPService service) {
        this.service = service;
    }

    @GetMapping("/{cep}")
    public EnderecoResponseDto buscar(@PathVariable String cep) {
        return service.getEndereco(cep);
    }

    
}