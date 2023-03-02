package br.com.juwer.algafoodapi.api.model.dto.input.itempedidodtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ItemPedidoInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long produtoId;

    @ApiModelProperty(example = "2", required = true)
    @NotNull
    @Positive
    private Integer quantidade;

    @ApiModelProperty(example = "Sem Alho")
    private String observacao;
}
