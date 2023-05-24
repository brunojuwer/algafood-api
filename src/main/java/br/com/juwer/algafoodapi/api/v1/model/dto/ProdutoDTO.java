package br.com.juwer.algafoodapi.api.v1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private boolean ativo;
}
