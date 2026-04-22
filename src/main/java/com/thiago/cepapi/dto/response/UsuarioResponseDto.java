package com.thiago.cepapi.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.thiago.cepapi.entity.Endereco;
import com.thiago.cepapi.entity.Usuario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UsuarioResponseDto {
    
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private List<EnderecoResponseDto> enderecos;

    public UsuarioResponseDto(Usuario usuario){

        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();

        List<Endereco> enderecos = usuario.getEnderecos();
        List<EnderecoResponseDto> dtos = new ArrayList<>();

        if(enderecos != null){
            for(Endereco endereco : enderecos){
                dtos.add(new EnderecoResponseDto(endereco));
            }
        }

        this.enderecos = dtos;

    }

}
