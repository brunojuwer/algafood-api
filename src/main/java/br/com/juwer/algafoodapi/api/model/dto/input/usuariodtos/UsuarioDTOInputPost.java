package br.com.juwer.algafoodapi.api.model.dto.input.usuariodtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioDTOInputPost extends UsuarioDTOInput{

    @ApiModelProperty(example = "minhasenhasecreta", required = true)
    @NotBlank
    private String senha;
}
