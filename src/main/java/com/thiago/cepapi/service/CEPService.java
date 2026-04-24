package com.thiago.cepapi.service;

import org.springframework.stereotype.Service;

import com.thiago.cepapi.client.CEPClient;
import com.thiago.cepapi.dto.response.CepApiResponse;
import com.thiago.cepapi.dto.response.EnderecoResponseDto;
import com.thiago.cepapi.exception.CepInvalidoException;


@Service
public class CEPService {
    
    private final CEPClient cepClient;

    public CEPService(CEPClient cepClient){
        this.cepClient = cepClient;
    }

    public EnderecoResponseDto buscarCep(String cep){

        try{

            CepApiResponse resposta = cepClient.buscarCep(cep);

            if(resposta == null || Boolean.TRUE.equals(resposta.getErro())){
                throw new CepInvalidoException();
            }

            EnderecoResponseDto dto  = new EnderecoResponseDto();

            dto.setCep(resposta.getCep());
            dto.setRua(resposta.getLogradouro());
            dto.setCidade(resposta.getLocalidade());
            dto.setEstado(resposta.getUf());
            dto.setBairro(resposta.getBairro());


            return dto;
            
        }catch(CepInvalidoException e){
            throw e;
        }catch(Exception e){
            throw new RuntimeException("Erro ao consultar API de CEP", e);
        }        

        

    }

}
