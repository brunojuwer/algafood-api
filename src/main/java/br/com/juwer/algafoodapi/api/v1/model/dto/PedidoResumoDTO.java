package br.com.juwer.algafoodapi.api.v1.model.dto;

import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteResumoDTO;
import br.com.juwer.algafoodapi.domain.model.StatusPedido;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoDTO extends RepresentationModel<PedidoResumoDTO> {

    private String codigo;

    private BigDecimal subTotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private StatusPedido status;

    private OffsetDateTime dataCriacao;

    private UsuarioDTO cliente;

    private RestauranteResumoDTO restaurante;
}
