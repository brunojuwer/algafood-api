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
    public void confirmar(String codigo) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(codigo);
        pedido.confirmar();
    }

    @Transactional
    public void cancelar(String codigo) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(codigo);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(String codigo) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(codigo);
        pedido.entregar();
    }

}
