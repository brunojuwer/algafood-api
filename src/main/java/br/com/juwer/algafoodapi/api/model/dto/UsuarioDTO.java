package br.com.juwer.algafoodapi.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    @ApiModelProperty(example = "2")
    private Long id;

    @ApiModelProperty(example = "João")
    private String nome;

    @ApiModelProperty(example = "joao@email.com")
    private String email;
}
