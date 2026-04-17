package com.thiago.cepapi.service;


import org.springframework.stereotype.Service;

import com.thiago.cepapi.client.CEPClient;
import com.thiago.cepapi.dto.request.EnderecoRequestDto;
import com.thiago.cepapi.dto.request.UsuarioRequestDto;
import com.thiago.cepapi.dto.response.EnderecoResponseDto;
import com.thiago.cepapi.dto.response.UsuarioResponseDto;
import com.thiago.cepapi.entity.Endereco;
import com.thiago.cepapi.entity.Usuario;



@Service
public class CEPService {
    
    private final CEPClient cepClient;

    public CEPService(CEPClient cepClient){
        this.cepClient = cepClient;
    }


    // funcao para teste de API
    public EnderecoResponseDto getEndereco(String cep){

        EnderecoResponseDto endereco = cepClient.buscarCep(cep);

        return endereco;

    }

    public UsuarioResponseDto cadastrarUsuario(UsuarioRequestDto dto){
        Usuario user = new Usuario(dto.getNome(), dto.getEmail(), dto.getTelefone());

        Endereco ende = cadastrarEndereco(dto.getEndereco());

        UsuarioResponseDto userDto = new UsuarioResponseDto(user, ende);
        
        return userDto;
    }


    private Endereco cadastrarEndereco(EnderecoRequestDto dto) {
        
        boolean flagCep = validaCep(dto.getCep());

        if(flagCep){
            Endereco e = new Endereco(dto.getCep(), dto.getRua(), dto.getNumero(), dto.getEstado(), dto.getCidade(), dto.getBairro());
            return e;
        }

        throw new IllegalArgumentException("Endereco Invalido");
        
    }


    private boolean validaCep(String cep) {
        
        if(cep == null){
            return false;
        }

        if(cep.length() != 8){
            return false;
        }

        for(char c : cep.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }

        return true;

    }




}
