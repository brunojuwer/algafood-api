package br.com.juwer.algafoodapi.api.model.dto;

import br.com.juwer.algafoodapi.domain.model.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PedidoDTO {

    private Long id;

    private BigDecimal subTotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;

    private EnderecoDTO enderecoEntrega;

    private StatusPedido status;

    private FormaPagamentoDTO formaPagamento;

    private UsuarioDTO cliente;

    private RestauranteResumoDTO restaurante;

    private List<ItemPedidoDTO> itens = new ArrayList<>();
}
