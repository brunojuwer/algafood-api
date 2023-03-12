package br.com.juwer.algafoodapi.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("PageModel")
@Data
public class PageModel {

    @ApiModelProperty(example = "10", value = "Quantidade de itens por página")
    private Long size;

    @ApiModelProperty(example = "50", value = "Quantidade de itens totais")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Quantidade total de páginas")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Página atual (Começa em 0)")
    private Long number;
}
