package br.com.juwer.algafoodapi.api.v1.model.dto.projections;

import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.EnderecoDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteDTO extends RepresentationModel<RestauranteDTO> {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;
    private Boolean ativo;
    private Boolean aberto;
    private EnderecoDTO endereco;
}
