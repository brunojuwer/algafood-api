package br.com.juwer.algafoodapi.api.v1.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoDTO {

    @ApiModelProperty(example = "1")
    private long id;

    @ApiModelProperty(example = "2")
    private Integer quantidade;

    @ApiModelProperty(example = "40", dataType = "java.lang.String")
    private BigDecimal precoUnitario;

    @ApiModelProperty(example = "80", dataType = "java.lang.String")
    private BigDecimal precoTotal;

    @ApiModelProperty(example = "Sem alho")
    private String observacao;

    private ProdutoDTO produto;
}
