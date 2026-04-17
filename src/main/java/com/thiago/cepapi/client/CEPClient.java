package com.thiago.cepapi.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.thiago.cepapi.dto.response.EnderecoResponseDto;

@Component
public class CEPClient {

    private final WebClient webClient;

    public CEPClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public EnderecoResponseDto buscarCep(String cep) {
        return webClient.get()
                .uri("/ws/{cep}/json/", cep)
                .retrieve()
                .bodyToMono(EnderecoResponseDto.class)
                .block();
    }
}