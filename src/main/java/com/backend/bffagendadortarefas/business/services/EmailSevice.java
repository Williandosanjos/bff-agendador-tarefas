package com.backend.bffagendadortarefas.business.services;

import com.backend.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.backend.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSevice {

    private final EmailClient notificacaoClient;

    public void enviarEmail(TarefasDTOResponse dto) {

        notificacaoClient.enviarEmail(dto);
    }

}
