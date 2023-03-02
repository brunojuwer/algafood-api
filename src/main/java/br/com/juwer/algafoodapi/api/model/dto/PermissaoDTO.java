package br.com.juwer.algafoodapi.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissaoDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "EDITAR_COZINHAS")
    private String nome;

    @ApiModelProperty(example = "Permite editar cozinhas")
    private String descricao;
}
