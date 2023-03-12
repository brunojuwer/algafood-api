package br.com.juwer.algafoodapi.api.v1.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cidades")
@Getter
@Setter
@ApiModel(value = "Cidade DTO", description = "Modelo de representação de uma cidade")
public class CidadeDTO extends RepresentationModel<CidadeDTO> {

    @ApiModelProperty(value = "ID da cidade", example = "1")
    private Long id;

    @ApiModelProperty(example = "Porto Alegre")
    private String nome;

    private EstadoDTO estado;
}
