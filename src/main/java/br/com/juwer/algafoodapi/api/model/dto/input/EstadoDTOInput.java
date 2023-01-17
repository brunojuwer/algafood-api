package br.com.juwer.algafoodapi.api.model.dto.input;

import javax.validation.constraints.NotBlank;

public class EstadoDTOInput {

    @NotBlank
    private String nome;
}
