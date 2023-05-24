package br.com.juwer.algafoodapi.api.v1.model.dto;

import br.com.juwer.algafoodapi.api.v1.model.dto.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozihas")
@Getter
@Setter
public class CozinhaDTO extends RepresentationModel<CozinhaDTO> {

    @JsonView(RestauranteView.Resumo.class)
    private Long id;
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
