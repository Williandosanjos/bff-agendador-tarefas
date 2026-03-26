package com.backend.bffagendadortarefas.business.services;


import com.backend.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.backend.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.backend.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.backend.bffagendadortarefas.infrastructure.enums.StatusTarefas;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;


    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {

        return tarefasClient.gravarTarefa(dto, token);
    }

    public List<TarefasDTOResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {

        return tarefasClient.buscaListarTarefasPorPeriodo(token, dataInicial, dataFinal);
    }

    public List<TarefasDTOResponse> buscaTarefaPorEmail(String token) {

        return tarefasClient.buscaTarefaPorEmail(token);
    }

    public void deletaTarefaPorId(String id,  String token) {

        tarefasClient.deletaTarefaPorId(token, id);
    }

    public TarefasDTOResponse alteraStatus(StatusTarefas statusTarefas, String id, String token) {

        return tarefasClient.alteraStatusNotificacao(statusTarefas, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {

        return tarefasClient.updateTarefas(dto, id, token);
    }
}
