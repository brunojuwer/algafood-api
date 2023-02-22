package br.com.juwer.algafoodapi.domain.event;

import br.com.juwer.algafoodapi.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {
    private Pedido pedido;
}
