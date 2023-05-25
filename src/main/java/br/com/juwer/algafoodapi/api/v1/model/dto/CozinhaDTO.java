package br.com.juwer.algafoodapi.api.v1.model.dto;

import br.com.juwer.algafoodapi.api.v1.model.dto.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozihas")
@Getter
@Setter
public class CozinhaDTO extends RepresentationModel<CozinhaDTO> {

    @Schema(example = "1")
    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @Schema(example = "Porto Alegre")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
