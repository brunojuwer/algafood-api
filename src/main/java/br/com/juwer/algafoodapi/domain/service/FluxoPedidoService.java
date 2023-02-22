package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.repository.PedidoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Autowired
    private PedidoRespository pedidoRespository;

    @Transactional
    public void confirmar(String codigo) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(codigo);
        pedido.confirmar();

        // necessário chamar o save para disparar os eventos do DDD
        pedidoRespository.save(pedido);
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
