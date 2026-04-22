package com.thiago.cepapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thiago.cepapi.entity.Endereco;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnderecoResponseDto {
    
    private Long id;
    private String cep;

    @JsonProperty("logradouro")
    private String rua;

    private String numero;
    private String estado;

    @JsonProperty("localidade")
    private String cidade;
    
    private String bairro;

    private Boolean erro;

    
    public EnderecoResponseDto(Endereco endereco) {
        this.id = endereco.getId();
        this.cep = endereco.getCep();
        this.rua = endereco.getRua();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.bairro = endereco.getBairro();
        this.numero = endereco.getNumero();
    }

    public EnderecoResponseDto(){}

}
