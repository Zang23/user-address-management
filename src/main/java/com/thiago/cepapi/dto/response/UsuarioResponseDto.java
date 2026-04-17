package com.thiago.cepapi.dto.response;




import com.thiago.cepapi.entity.Endereco;
import com.thiago.cepapi.entity.Usuario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UsuarioResponseDto {
    
    private String nome;
    private String email;
    private String telefone;
    private EnderecoResponseDto endereco;

    public UsuarioResponseDto(Usuario usuario, Endereco ende){

        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.endereco = new EnderecoResponseDto(ende);

    }

}
