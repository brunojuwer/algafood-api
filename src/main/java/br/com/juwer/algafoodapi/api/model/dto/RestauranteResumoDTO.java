package br.com.juwer.algafoodapi.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
@ApiModel(description = "Representação resumida do restaurante")
public class RestauranteResumoDTO extends RepresentationModel<RestauranteDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Yamamoto's Food")
    private String nome;
}
