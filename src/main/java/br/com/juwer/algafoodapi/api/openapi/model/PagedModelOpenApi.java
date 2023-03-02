package br.com.juwer.algafoodapi.api.openapi.model;

import br.com.juwer.algafoodapi.api.model.dto.CozinhaDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedModelOpenApi<T> {
    @ApiModelProperty(value = "Conteúdo")
    private List<T> content;

    @ApiModelProperty(example = "10", value = "Quantidade de itens por página")
    private Long tamanhoPorPagina;

    @ApiModelProperty(example = "50", value = "Quantidade de itens totais")
    private Long elementosTotais;

    @ApiModelProperty(example = "5", value = "Quantidade total de páginas")
    private Long paginasTotais;

    @ApiModelProperty(example = "0", value = "Página atual (Começa em 0)")
    private Long paginaAtual;
}
