package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.exception.PedidoNaoEncontradoException;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.repository.PedidoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRespository pedidoRespository;


    public Pedido buscaOuFalha(Long pedidoId) {
        return pedidoRespository.findById(pedidoId).orElseThrow(
                () -> new PedidoNaoEncontradoException(pedidoId)
        );
    }
}
