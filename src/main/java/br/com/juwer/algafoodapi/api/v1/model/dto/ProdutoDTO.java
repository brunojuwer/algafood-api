package br.com.juwer.algafoodapi.api.v1.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> {

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
