package com.thiago.cepapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequestDto {
    
    private String cep;
    private String numero;

    public EnderecoRequestDto(){}

}
