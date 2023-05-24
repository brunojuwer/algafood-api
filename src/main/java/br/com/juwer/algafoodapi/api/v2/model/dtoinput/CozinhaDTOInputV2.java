package br.com.juwer.algafoodapi.api.v2.model.dtoinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaDTOInputV2 {

    @NotBlank
    private String nomeCozinha;
}
