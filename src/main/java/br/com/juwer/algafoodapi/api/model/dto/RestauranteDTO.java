package br.com.juwer.algafoodapi.api.model.dto;

import br.com.juwer.algafoodapi.api.model.dto.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNomeEId.class})
    private Long id;

    @ApiModelProperty(example = "Tuk Tuk Food")
    @JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNomeEId.class})
    private String nome;

    @ApiModelProperty(example = "20,00", dataType = "java.lang.String")
    @JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;

    @JsonView(RestauranteView.Resumo.class)
    private CozinhaDTO cozinha;

    private Boolean ativo;
    private Boolean aberto;
    private EnderecoDTO endereco;
}
