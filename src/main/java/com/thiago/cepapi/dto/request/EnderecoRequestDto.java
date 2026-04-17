package com.thiago.cepapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequestDto {
    
    private String cep;
    private String rua;
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;

    public EnderecoRequestDto(){}

}
