package br.com.juwer.algafoodapi.api.model.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoDTOInput {


    @ApiModelProperty(example = "99999-000", required = true)
    @NotBlank
    private String cep;

    @ApiModelProperty(example = "Rua Tiradentes", required = true)
    @NotBlank
    private String logradouro;

    @ApiModelProperty(example = "123A", required = true)
    @NotBlank
    private String numero;

    @ApiModelProperty(example = "Casa perto do mercado")
    private String complemento;

    @ApiModelProperty(example = "Centro")
    @NotBlank
    private String bairro;

    @ApiModelProperty(required = true)
    @Valid
    @NotNull
    private CidadeDTOIdInput cidade;

}
