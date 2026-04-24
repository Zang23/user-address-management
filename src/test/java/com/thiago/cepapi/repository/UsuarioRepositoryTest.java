package com.thiago.cepapi.repository;

import com.thiago.cepapi.entity.Endereco;
import com.thiago.cepapi.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve encontrar um usuário por e-mail quando ele existir")
    void existsByEmailCenario01() {
        Usuario usuario = new Usuario("Thiago", "thiago@email.com", "11999999999");
        usuarioRepository.save(usuario);

        boolean existe = usuarioRepository.existsByEmail("thiago@email.com");
        assertTrue(existe);
    }

    @Test
    @DisplayName("Deve retornar false quando buscar e-mail inexistente")
    void existsByEmailCenario02() {
        boolean existe = usuarioRepository.existsByEmail("inexistente@email.com");
        assertFalse(existe);
    }

    @Test
    @DisplayName("Deve verificar a existência por telefone")
    void existsByTelefoneCenario01() {
        Usuario usuario = new Usuario("Thiago", "thiago2@email.com", "11888888888");
        usuarioRepository.save(usuario);

        assertTrue(usuarioRepository.existsByTelefone("11888888888"));
    }

    @Test
    @DisplayName("Deve salvar usuário e persistir endereços em cascata")
    void salvarUsuarioComEndereco() {
        Usuario usuario = new Usuario("Thiago", "thiago@email.com", "11999999999");
        Endereco endereco = new Endereco("12345678", "Rua A", "10", "SP", "Cidade", "Bairro");
        endereco.setUsuario(usuario);
        usuario.setEnderecos(List.of(endereco));

        Usuario salvo = usuarioRepository.save(usuario);

        assertNotNull(salvo.getId());
        assertEquals(1, salvo.getEnderecos().size());
        assertNotNull(salvo.getEnderecos().get(0).getId());
    }
}