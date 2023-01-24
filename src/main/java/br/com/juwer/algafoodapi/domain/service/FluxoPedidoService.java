package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(pedidoId);
        pedido.confirmar();
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(pedidoId);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(pedidoId);
        pedido.entregar();
    }

}
