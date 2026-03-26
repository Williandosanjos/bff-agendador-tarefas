package com.backend.bffagendadortarefas.infrastructure.client;

import com.backend.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.backend.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.backend.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.backend.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.backend.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.backend.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.backend.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPormail(@RequestParam("email") String email,
                                           @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);
    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastrarEndereco(@RequestBody EnderecoDTORequest dto,
                                          @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastroTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String token);
}
