package com.thiago.cepapi.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDto {
    
    private String nome;
    private String email;
    private String telefone;
    private List<EnderecoRequestDto> enderecos;

    public UsuarioRequestDto() {}



}
