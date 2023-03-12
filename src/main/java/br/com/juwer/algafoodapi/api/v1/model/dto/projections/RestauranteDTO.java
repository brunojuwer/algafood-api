package br.com.juwer.algafoodapi.api.v1.model.dto.projections;

import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.EnderecoDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteDTO extends RepresentationModel<RestauranteDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Tuk Tuk Food")
    private String nome;

    @ApiModelProperty(example = "20,00", dataType = "java.lang.String")
    private BigDecimal taxaFrete;

    private CozinhaDTO cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoDTO endereco;
}
