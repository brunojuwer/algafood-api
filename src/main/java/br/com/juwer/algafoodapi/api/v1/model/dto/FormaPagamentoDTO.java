package br.com.juwer.algafoodapi.api.v1.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "formasPagamento")
@Getter
@Setter
public class FormaPagamentoDTO extends RepresentationModel<FormaPagamentoDTO> {

    @ApiModelProperty(example = "1", position = 1)
    private Long id;

    @ApiModelProperty(example = "Dinheiro", position = 5)
    private String descricao;
}
