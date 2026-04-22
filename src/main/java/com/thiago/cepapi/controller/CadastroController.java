package com.thiago.cepapi.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiago.cepapi.dto.request.UsuarioRequestDto;
import com.thiago.cepapi.dto.response.UsuarioResponseDto;
import com.thiago.cepapi.service.CadastroService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class CadastroController {

    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> cadastrar(@RequestBody UsuarioRequestDto dto) {
        UsuarioResponseDto resposta = cadastroService.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar(){
        return ResponseEntity.ok(cadastroService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscar(@PathVariable Long id){

        return ResponseEntity.ok(cadastroService.buscarUsuarioId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDto dto){
    
        return ResponseEntity.ok(cadastroService.atualizarUsuario(id, dto));

    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        cadastroService.deletarUsuario(id);
        return ResponseEntity.noContent().build();

    }
    

    

    
}