package br.com.juwer.algafoodapi.api.v1.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "grupos")
@Getter
@Setter
@ApiModel(value = "Grupo DTO", description = "Modelo de representação de um grupo")
public class GrupoDTO extends RepresentationModel<GrupoDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Gerente")
    private String nome;
}
