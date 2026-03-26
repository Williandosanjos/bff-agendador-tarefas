package com.backend.bffagendadortarefas.business.services;

import com.backend.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.backend.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.backend.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.backend.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.backend.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.backend.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.backend.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.backend.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient cliente;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {

        return cliente.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequestDTO usuarioDTO) {

        return cliente.login(usuarioDTO);
    }

    public UsuarioDTOResponse BuscarUsuarioPorEmail(String email, String token){

        return cliente.buscaUsuarioPormail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){

        cliente.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {

        return cliente.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {

        return cliente.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {

        return cliente.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastrarEndereco(String token, EnderecoDTORequest dto) {

        return cliente.cadastrarEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastrarTelefone(String token, TelefoneDTORequest dto) {

        return cliente.cadastroTelefone(dto, token);
    }
}
