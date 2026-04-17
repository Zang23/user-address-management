package com.thiago.cepapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDto {
    
    private String nome;
    private String email;
    private String telefone;
    private EnderecoRequestDto endereco;

    public UsuarioRequestDto() {}



}
