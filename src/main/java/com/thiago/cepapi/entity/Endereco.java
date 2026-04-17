package com.thiago.cepapi.entity;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Endereco {
    
    private String cep;
    private String rua;
    private String numero;
    private String estado;
    private String cidade;
    private String bairro;


    public Endereco(String cep, String rua, String numero, String estado, String cidade, String bairro){

        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;

    }


}
