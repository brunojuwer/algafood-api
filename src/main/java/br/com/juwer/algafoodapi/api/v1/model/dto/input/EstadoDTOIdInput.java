package br.com.juwer.algafoodapi.api.v1.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstadoDTOIdInput {

    @NotNull
    private Long id;
}