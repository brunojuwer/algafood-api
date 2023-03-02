package br.com.juwer.algafoodapi.api.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(value = "Pageable")
public class PageableModelOpenApi {

    @ApiModelProperty(value = " Número da página começa em 0", example = "0")
    private int page;

    @ApiModelProperty(value = "Quantidade de itens por página", example = "10")
    private int size;

    @ApiModelProperty(value = "Nome da propriedade para ordenação", example = "nome,asc")
    private List<String> sort;
}
