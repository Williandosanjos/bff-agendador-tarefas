package com.backend.bffagendadortarefas.business.dto.in;

import com.backend.bffagendadortarefas.infrastructure.enums.StatusTarefas;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTORequest {

    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataEvento;

}
