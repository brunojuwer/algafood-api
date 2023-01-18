package br.com.juwer.algafoodapi.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrupoDTOInput {

    @NotBlank
    private String nome;
}
