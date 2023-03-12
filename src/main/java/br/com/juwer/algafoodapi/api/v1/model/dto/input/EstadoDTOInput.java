package br.com.juwer.algafoodapi.api.v1.model.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EstadoDTOInput {

    @ApiModelProperty(example = "Rio Grande do Sul", required = true)
    @NotBlank
    private String nome;
}
