package com.thiago.cepapi.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Endereco {
    

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
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

    public Endereco(){}


}
