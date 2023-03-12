package br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioDTOInputSenha {

    @ApiModelProperty(example = "minhasenhasecreta", required = true)
    @NotBlank
    private String senhaAtual;

    @ApiModelProperty(example = "minhanovasenhasecreta", required = true)
    @NotBlank
    private String senha;
}
