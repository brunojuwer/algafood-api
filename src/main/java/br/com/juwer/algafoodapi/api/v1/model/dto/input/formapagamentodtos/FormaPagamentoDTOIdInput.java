package br.com.juwer.algafoodapi.api.v1.model.dto.input.formapagamentodtos;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoDTOIdInput {

    @NotNull
    private Long id;
}
