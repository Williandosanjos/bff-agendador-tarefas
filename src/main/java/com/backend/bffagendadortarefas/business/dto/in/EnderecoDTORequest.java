package com.backend.bffagendadortarefas.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTORequest {

    private String rua;

    private Long numero;

    private String bairro;

    private String complemento;

    private String cidade;

    private String estado;

    private String cep;
}
