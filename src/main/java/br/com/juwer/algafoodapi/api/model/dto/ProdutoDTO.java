package br.com.juwer.algafoodapi.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Sushi")
    private String nome;

    @ApiModelProperty(example = "Sushi tradicional")
    private String descricao;

    @ApiModelProperty(example = "40", dataType = "java.lang.String")
    private BigDecimal preco;

    @ApiModelProperty(example = "true")
    private boolean ativo;
}
