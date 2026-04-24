package com.thiago.cepapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiago.cepapi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
    
}
