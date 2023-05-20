package br.com.juwer.algafoodapi.api.v1.openapi.model;

import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("RestauranteResumoModel")
public class RestauranteModelResumoOpenApi {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Yamamoto's Food")
    private String nome;

    @ApiModelProperty(example = "20,00", dataType = "java.lang.String")
    private BigDecimal taxaFrete;

    private CozinhaDTO cozinha;
}
