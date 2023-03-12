package br.com.juwer.algafoodapi.api.v1.disassembler;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.pedidosdto.PedidoDTOInput;
import br.com.juwer.algafoodapi.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class PedidoDTODisassembler extends GenericDisassembler<PedidoDTOInput, Pedido> {

    @Override
    public void copyToDomainObject(PedidoDTOInput pedidoDTOInput, Pedido pedido) {
        pedido.setRestaurante(new Restaurante());
        pedido.setFormaPagamento(new FormaPagamento());
        pedido.getItens().forEach(item -> new ItemPedido());

        if(pedido.getEnderecoEntrega().getCidade().getId() != null) {
            pedido.getEnderecoEntrega().setCidade(new Cidade());
        }

        modelMapper.map(pedidoDTOInput, pedido);

    }
}
