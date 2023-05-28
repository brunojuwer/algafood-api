package br.com.juwer.algafoodapi.api.v1.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeDTOInput {

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoDTOIdInput estado;
}
