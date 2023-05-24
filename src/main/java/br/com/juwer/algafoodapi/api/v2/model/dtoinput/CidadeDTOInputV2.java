package br.com.juwer.algafoodapi.api.v2.model.dtoinput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeDTOInputV2 {

    @NotBlank
    private String nomeCidade;

    @NotNull
    private Long idEstado;
}
