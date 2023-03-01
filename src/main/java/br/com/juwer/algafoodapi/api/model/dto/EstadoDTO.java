package br.com.juwer.algafoodapi.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO {

    @ApiModelProperty(value = "ID de um Estado", example = "1")
    private Long id;

    @ApiModelProperty(example = "Rio Grande do Sul")
    private String nome;
}
