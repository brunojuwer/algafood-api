package br.com.juwer.algafoodapi.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("cidades")
@Getter
@Setter
@ApiModel(description = "Representação resumida de uma Cidade")
public class CidadeResumoDTO extends RepresentationModel<CidadeResumoDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Porto Alegre")
    private String nome;

    @ApiModelProperty(example = "Rio Grande do Sul")
    private String estado;
}
