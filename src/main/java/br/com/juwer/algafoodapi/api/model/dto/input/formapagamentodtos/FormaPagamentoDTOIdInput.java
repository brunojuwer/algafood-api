package br.com.juwer.algafoodapi.api.model.dto.input.formapagamentodtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoDTOIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
