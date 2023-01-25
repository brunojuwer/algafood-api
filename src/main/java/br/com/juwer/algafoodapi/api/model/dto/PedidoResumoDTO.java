package br.com.juwer.algafoodapi.api.model.dto;

import br.com.juwer.algafoodapi.domain.model.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PedidoResumoDTO {

    private String codigo;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private OffsetDateTime dataCriacao;
    private UsuarioDTO cliente;
    private RestauranteResumoDTO restaurante;
}
