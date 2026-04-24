package com.thiago.cepapi.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thiago.cepapi.dto.request.EnderecoRequestDto;
import com.thiago.cepapi.dto.request.UsuarioRequestDto;
import com.thiago.cepapi.exception.CepFormatoInvalidoException;
import com.thiago.cepapi.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CadastroServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CEPService cepService;

    @InjectMocks
    private CadastroService cadastroService;

    @Test
    @DisplayName("Deve retornar true para um CEP válido com 8 dígitos numéricos")
    void validaFormatoCepCenario01() {
        assertTrue(cadastroService.validaFormatoCep("12345678"));
    }

    @Test
    @DisplayName("Deve retornar false para CEP com letras")
    void validaFormatoCepCenario02() {
        assertFalse(cadastroService.validaFormatoCep("12345a78"));
    }

    @Test
    @DisplayName("Deve retornar false para CEP com tamanho inválido")
    void validaFormatoCepCenario03() {
        assertFalse(cadastroService.validaFormatoCep("12345"));
        assertFalse(cadastroService.validaFormatoCep("123456789"));
    }

    @Test
    @DisplayName("Deve retornar false para CEP nulo")
    void validaFormatoCepCenario04() {
        assertFalse(cadastroService.validaFormatoCep(null));
    }

    
}