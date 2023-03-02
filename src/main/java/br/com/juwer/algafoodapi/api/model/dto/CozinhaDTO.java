package br.com.juwer.algafoodapi.api.model.dto;

import br.com.juwer.algafoodapi.api.model.dto.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "CozinhaDTO")
public class CozinhaDTO {

    @ApiModelProperty(example = "1")
    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @ApiModelProperty(example = "Japonesa")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
