package br.com.juwer.algafoodapi.api.v2.model.dtoinput;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeDTOInputV2 {

    @NotBlank
    private String nomeCidade;

    @NotNull
    private Long idEstado;
}
