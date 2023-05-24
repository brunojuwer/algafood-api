package br.com.juwer.algafoodapi.api.v1.model.dto.projections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteResumoDTO extends RepresentationModel<RestauranteResumoDTO> {

    private Long id;
    private String nome;
}
