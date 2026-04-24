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
import com.thiago.cepapi.exception.CepFormatoInvalidoException;
import com.thiago.cepapi.exception.DuplicidadeException;
import com.thiago.cepapi.exception.RecursoNaoEncontradoException;
import com.thiago.cepapi.repository.UsuarioRepository;

import oracle.security.o3logon.a;

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

        if(usuarioRepository.existsByEmail(dto.getEmail())){
            throw new DuplicidadeException("Email ja cadastrado");
        }

        if(usuarioRepository.existsByTelefone(dto.getTelefone())){
            throw new DuplicidadeException("Telfone ja cadastrado");
        }

        Usuario usuario = new Usuario(dto.getNome(), dto.getEmail(), dto.getTelefone());

        List<Endereco> enderecos = new ArrayList<>();

        for(EnderecoRequestDto enderecoDto : dto.getEnderecos()){
            Endereco endereco = cadastrarEndereco(enderecoDto);

            endereco.setUsuario(usuario); 

            enderecos.add(endereco);
        }

        usuario.setEnderecos(enderecos);

        Usuario salvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDto(salvo);
    }   


    private Endereco cadastrarEndereco(EnderecoRequestDto dto) {
            
        if(!validaFormatoCep(dto.getCep())){
            throw new CepFormatoInvalidoException();
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

        for(Usuario usuario : usuarios){

            resposta.add(new UsuarioResponseDto(usuario));

        }

        return resposta;
    }


    public UsuarioResponseDto buscarUsuarioId(Long id) {
        
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        return new UsuarioResponseDto(usuario);
        
    }


    public UsuarioResponseDto atualizarUsuario(Long id, UsuarioRequestDto dto) {

    Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

    
    usuario.setNome(dto.getNome());
    usuario.setEmail(dto.getEmail());
    usuario.setTelefone(dto.getTelefone());

    List<Endereco> enderecosAtuais = usuario.getEnderecos();
    List<Endereco> novosEnderecos = new ArrayList<>();

    for (EnderecoRequestDto enderecoDto : dto.getEnderecos()) {

        if (!validaFormatoCep(enderecoDto.getCep())) {
            throw new CepFormatoInvalidoException();
        }

        EnderecoResponseDto resposta = cepService.buscarCep(enderecoDto.getCep());

        if (enderecoDto.getId() != null) {

            Endereco existente = null;

            for (Endereco e : enderecosAtuais) {
                if (e.getId().equals(enderecoDto.getId())) {
                    existente = e;
                    break;
                }
            }

            if (existente == null) {
                throw new RecursoNaoEncontradoException("Endereco nao encontrado");
            }

            
            existente.setCep(enderecoDto.getCep());
            existente.setNumero(enderecoDto.getNumero());
            existente.setRua(resposta.getRua());
            existente.setCidade(resposta.getCidade());
            existente.setEstado(resposta.getEstado());
            existente.setBairro(resposta.getBairro());

            novosEnderecos.add(existente);

        } else {

            Endereco novo = new Endereco(
                enderecoDto.getCep(),
                resposta.getRua(),
                enderecoDto.getNumero(),
                resposta.getEstado(),
                resposta.getCidade(),
                resposta.getBairro()
            );

            novo.setUsuario(usuario);
            novosEnderecos.add(novo);
        }
    }

    enderecosAtuais.clear();
    enderecosAtuais.addAll(novosEnderecos);

    Usuario salvo = usuarioRepository.save(usuario);

    return new UsuarioResponseDto(salvo);
}


    public void deletarUsuario(Long id) {
        
        if(!usuarioRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Usuario nao encontrado");
        }

        usuarioRepository.deleteById(id);

    }




}
