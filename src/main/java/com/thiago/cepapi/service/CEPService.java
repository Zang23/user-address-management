package com.thiago.cepapi.service;

import org.springframework.stereotype.Service;

import com.thiago.cepapi.client.CEPClient;
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

            EnderecoResponseDto resposta = cepClient.buscarCep(cep);

            if(resposta == null || Boolean.TRUE.equals(resposta.getErro())){
                throw new CepInvalidoException();
            }

            return resposta;
            
        }catch(CepInvalidoException e){
            throw e;
        }catch(Exception e){
            throw new RuntimeException("Erro ao consultar API de CEP", e);
        }        

        

    }

}
