package br.com.juwer.algafoodapi.api.v1.model.dto.projections;

import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteBasicoDTO extends RepresentationModel<RestauranteBasicoDTO> {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;
}
