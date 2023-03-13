package br.com.juwer.algafoodapi.api.v2.model.dtoinput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaDTOInputV2 {

    @ApiModelProperty(example = "Japonesa", required = true)
    @NotBlank
    private String nomeCozinha;
}
