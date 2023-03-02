package br.com.juwer.algafoodapi.api.model.dto.input.formapagamentodtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FormaPagamentoDTOInput {

    @ApiModelProperty(example = "Dinheiro", required = true)
    @NotBlank
    private String descricao;
}
