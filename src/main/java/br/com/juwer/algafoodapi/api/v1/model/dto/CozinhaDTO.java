package br.com.juwer.algafoodapi.api.v1.model.dto;

import br.com.juwer.algafoodapi.api.v1.model.dto.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozihas")
@Getter
@Setter
@ApiModel(value = "CozinhaDTO")
public class CozinhaDTO extends RepresentationModel<CozinhaDTO> {

    @ApiModelProperty(example = "1")
    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    @ApiModelProperty(example = "Japonesa")
    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
