package br.com.juwer.algafoodapi.api.v1.model.dto;

import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteResumoDTO;
import br.com.juwer.algafoodapi.domain.model.StatusPedido;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
public class PedidoDTO extends RepresentationModel<PedidoDTO> {

    private String codigo;

    private BigDecimal subTotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private StatusPedido status;

    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;


    private EnderecoDTO enderecoEntrega;
    private FormaPagamentoDTO formaPagamento;
    private UsuarioDTO cliente;
    private RestauranteResumoDTO restaurante;
    private List<ItemPedidoDTO> itens = new ArrayList<>();
}
