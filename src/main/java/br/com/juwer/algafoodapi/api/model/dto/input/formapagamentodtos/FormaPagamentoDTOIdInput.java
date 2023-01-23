package br.com.juwer.algafoodapi.api.model.dto.input.formapagamentodtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoDTOIdInput {

    @NotNull
    private Long id;
}
