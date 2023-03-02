package br.com.juwer.algafoodapi.api.model.dto.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel(description = "Representação do modelo de Input do grupo")
public class GrupoDTOInput {

    @ApiModelProperty(example = "Gerente", required = true)
    @NotBlank
    private String nome;
}
