package com.backend.bffagendadortarefas.infrastructure.client;

import com.backend.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.backend.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.backend.bffagendadortarefas.infrastructure.enums.StatusTarefas;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravarTarefa(@RequestBody TarefasDTORequest dto,
                                    @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListarTarefasPorPeriodo(@RequestHeader("Authorization") String token,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefaPorEmail(@RequestHeader("Authorization") String token);


    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);


    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacao(@RequestParam("status") StatusTarefas statusTarefa,
                                               @RequestParam("id") String id,
                                               @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestParam("id") String id,
                                     @RequestHeader("Authorization") String token);
}
