package br.com.juwer.algafoodapi.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoDTO {

    @ApiModelProperty(example = "99999-000")
    private String cep;

    @ApiModelProperty(example = "Rua Tiradentes")
    private String logradouro;

    @ApiModelProperty(example = "123A")
    private String numero;

    @ApiModelProperty(example = "Ao lado do Mercado")
    private String complemento;

    @ApiModelProperty(example = "Centro")
    private String bairro;

    private CidadeResumoDTO cidade;
}
