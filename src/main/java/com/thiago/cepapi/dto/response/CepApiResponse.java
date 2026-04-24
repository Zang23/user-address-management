package com.thiago.cepapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class CepApiResponse {

    private String cep;

    @JsonProperty("logradouro")
    private String logradouro;

    private String bairro;

    @JsonProperty("localidade")
    private String localidade;

    @JsonProperty("uf")
    private String uf;

    private Boolean erro;
}
