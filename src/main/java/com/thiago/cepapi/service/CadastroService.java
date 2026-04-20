package com.thiago.cepapi.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.thiago.cepapi.dto.request.EnderecoRequestDto;
import com.thiago.cepapi.dto.request.UsuarioRequestDto;
import com.thiago.cepapi.dto.response.EnderecoResponseDto;
import com.thiago.cepapi.dto.response.UsuarioResponseDto;
import com.thiago.cepapi.entity.Endereco;
import com.thiago.cepapi.entity.Usuario;
import com.thiago.cepapi.repository.UsuarioRepository;
import org.springframework.web.server.ResponseStatusException;



@Service
public class CadastroService {
    
    private final CEPService cepService;
    private final UsuarioRepository usuarioRepository;

    public CadastroService(CEPService cepService, UsuarioRepository usuarioRepository ){
        this.cepService = cepService;
        this.usuarioRepository = usuarioRepository;
    }


    public UsuarioResponseDto cadastrarUsuario(UsuarioRequestDto dto){


        Endereco endereco = cadastrarEndereco(dto.getEndereco());

        Usuario user = new Usuario(dto.getNome(), dto.getEmail(), dto.getTelefone());
        user.setEndereco(endereco);

        Usuario salvo = usuarioRepository.save(user);
        
        
        UsuarioResponseDto userDto = new UsuarioResponseDto(salvo, salvo.getEndereco()); 
        return userDto;
    }


    private Endereco cadastrarEndereco(EnderecoRequestDto dto) {
            
        if(!validaFormatoCep(dto.getCep())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de CEP invalido");
        }

        EnderecoResponseDto resposta = cepService.buscarCep(dto.getCep());
        
        Endereco e = new Endereco(dto.getCep(), resposta.getRua(), dto.getNumero(), resposta.getEstado(), resposta.getCidade(), resposta.getBairro());
        return e;  

    }


    private boolean validaFormatoCep(String cep) {
        
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


    public List<UsuarioResponseDto> listarUsuarios() {
        
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDto> resposta = new ArrayList<>();

        for(Usuario user : usuarios){

            resposta.add(new UsuarioResponseDto(user, user.getEndereco()));

        }

        return resposta;
    }


    public UsuarioResponseDto buscarUsuarioId(Long id) {
        
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario nao encontrado"));

        return new UsuarioResponseDto(usuario, usuario.getEndereco());
        
    }


    public UsuarioResponseDto atualizarUsuario(Long id, UsuarioRequestDto dto) {
        
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario nao encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());

        Endereco endereco = usuario.getEndereco();

        if(!validaFormatoCep(dto.getEndereco().getCep())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de CEP invalido");
        }
        
        endereco.setCep(dto.getEndereco().getCep());
        endereco.setNumero(dto.getEndereco().getNumero());

        EnderecoResponseDto resposta = cepService.buscarCep(dto.getEndereco().getCep());

        endereco.setBairro(resposta.getBairro());
        endereco.setEstado(resposta.getEstado());
        endereco.setRua(resposta.getRua());
        endereco.setCidade(resposta.getCidade());

        Usuario salvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDto(salvo, salvo.getEndereco());

    }


    public void deletarUsuario(Long id) {
        
        if(!usuarioRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario nao encontrado");
        }

        usuarioRepository.deleteById(id);

    }




}
