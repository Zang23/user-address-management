package com.thiago.cepapi.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Usuario {
    
    private String nome;
    private String email;
    private String telefone;     
    
    public Usuario (String nome, String email, String telefone){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

}
