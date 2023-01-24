package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(pedidoId);

        if(!pedido.getStatus().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(String.format(
                    "Status do pedido %d não pode ser alterado de %s para %s",
                    pedidoId, pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()
            ));
        }

        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(pedidoId);

        if(!pedido.getStatus().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(String.format(
                    "Status do pedido %d não pode ser alterado de %s para %s",
                    pedidoId, pedido.getStatus().getDescricao(), StatusPedido.CANCELADO.getDescricao()
            ));
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        pedido.setDataCancelamento(OffsetDateTime.now());
    }

    @Transactional
    public void entregar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(pedidoId);

        if(!pedido.getStatus().equals(StatusPedido.CONFIRMADO)) {
            throw new NegocioException(String.format(
                    "Status do pedido %d não pode ser alterado de %s para %s",
                    pedidoId, pedido.getStatus().getDescricao(), StatusPedido.ENTREGE.getDescricao()
            ));
        }

        pedido.setStatus(StatusPedido.ENTREGE);
        pedido.setDataEntrega(OffsetDateTime.now());
    }

}
