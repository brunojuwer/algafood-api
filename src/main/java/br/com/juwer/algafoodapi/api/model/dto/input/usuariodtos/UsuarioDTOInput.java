package br.com.juwer.algafoodapi.api.model.dto.input.usuariodtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UsuarioDTOInput {

    @ApiModelProperty(example = "João", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "joao@email.com", required = true)
    @NotBlank
    @Email
    private String email;
}
