package br.com.juwer.algafoodapi.api.model.dto.input.formapagamentodtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FormaPagamentoDTOInput {

    @NotBlank
    private String descricao;
}
