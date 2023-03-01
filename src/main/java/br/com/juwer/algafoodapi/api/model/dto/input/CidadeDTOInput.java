package br.com.juwer.algafoodapi.api.model.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeDTOInput {

    @ApiModelProperty(example = "Porto Alegre", required = true)
    @NotBlank
    private String nome;

    @Valid
    @NotNull
    private EstadoDTOIdInput estado;
}
