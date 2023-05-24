package br.com.juwer.algafoodapi.api.v2.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozihas")
@Getter
@Setter
public class CozinhaDTOV2 extends RepresentationModel<CozinhaDTOV2> {

    private Long idCozinha;

    private String nomeCozinha;
}
