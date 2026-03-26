package com.backend.bffagendadortarefas.controller;

import com.backend.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.backend.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.backend.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.backend.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.backend.bffagendadortarefas.business.services.UsuarioService;
import com.backend.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.backend.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.backend.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuário")
public class UsuarioController {

    private final UsuarioService usuarioServiceService;

    @PostMapping
    @Operation(summary = "Salvar Usuario", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioServiceService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuário", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO){

        return usuarioServiceService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar Dados de Usuário por Email", description = "Busca o usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse>buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                  @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioServiceService.BuscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuário por Id", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader("Authorization") String token){
        usuarioServiceService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar Dados de Usuário", description = "Atualizar dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioServiceService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar Endereço de Usuário", description = "Atualizar endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioServiceService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar Telefone de Usuário", description = "Atualizar telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefones atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioServiceService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastra Novo Endereço de Usuário", description = "Cadastra novo endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Novo endereço Cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastrarEndereco(@RequestBody EnderecoDTORequest dto,
                                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioServiceService.cadastrarEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastra Novo Telefone de Usuário", description = "Cadastra novo telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Novo telefone Cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastroTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioServiceService.cadastrarTelefone(token, dto));
    }
}
