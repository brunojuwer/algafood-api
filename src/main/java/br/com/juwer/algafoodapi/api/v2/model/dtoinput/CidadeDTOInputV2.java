package br.com.juwer.algafoodapi.api.v2.model.dtoinput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeDTOInputV2 {

    @ApiModelProperty(example = "Porto Alegre", required = true)
    @NotBlank
    private String nomeCidade;

    @NotNull
    private Long idEstado;
}
