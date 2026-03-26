package com.backend.bffagendadortarefas.controller;

import com.backend.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.backend.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.backend.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.backend.bffagendadortarefas.business.services.TarefasService;
import com.backend.bffagendadortarefas.infrastructure.enums.StatusTarefas;
import com.backend.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "agendador-tarefas", description = "Cadastra tarefas de usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar Tarefa Usuario", description = "Cria um novo tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salvo com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefa(@RequestBody TarefasDTORequest dto,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca Tarefas Usuario por Período", description = "Busca tarefas cadastradas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListarTarefasPorPeriodo(
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal) {

        return ResponseEntity.ok(tarefasService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));

    }

    @GetMapping
    @Operation(summary = "Busca Tarefas por Email Usuario", description = "Busca tarefas cadastradas por email")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefaPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefaPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta Tarefas por Id", description = "Deleta tarefas cadastradas por id")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {

        tarefasService.deletaTarefaPorId(id, token);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera Status da Tarefas", description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da tarefas alterada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusTarefas statusTarefas,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token){

        return ResponseEntity.ok(tarefasService.alteraStatus(statusTarefas, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera Dados de Tarefas", description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alterada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}
