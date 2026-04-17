package com.thiago.cepapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;

import com.thiago.cepapi.dto.response.EnderecoResponseDto;
import com.thiago.cepapi.service.CEPService;

@SpringBootApplication
public class CepApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CepApiApplication.class, args);

	}

}
