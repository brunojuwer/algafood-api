package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.controller.PedidoController;
import br.com.juwer.algafoodapi.api.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.api.utils.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public PedidoDTOAssembler() {
        super(PedidoController.class, PedidoDTO.class);
    }

    @Override
    public PedidoDTO toModel(Pedido pedido) {
        PedidoDTO pedidoDTO = modelMapper.map(pedido, PedidoDTO.class);

        pedidoDTO.add(hateoasAlgaLinks.linkToPedidos(pedidoDTO.getCodigo()));

        pedidoDTO.add(hateoasAlgaLinks.linkToPedidos());

        pedidoDTO.getEnderecoEntrega().getCidade()
                .add(hateoasAlgaLinks.linkToCidade(pedidoDTO.getEnderecoEntrega().getCidade().getId()));

        pedidoDTO.getFormaPagamento()
                .add(hateoasAlgaLinks.linkToFormaPagamento(pedidoDTO.getFormaPagamento().getId()));

        pedidoDTO.getCliente().add(hateoasAlgaLinks.linkToCliente(pedidoDTO.getCliente().getId()));

        pedidoDTO.getRestaurante().add(hateoasAlgaLinks.linkToRestaurante(pedidoDTO.getRestaurante().getId()));

        pedidoDTO.getItens().forEach(itemPedidoDTO -> {
            itemPedidoDTO.getProduto().add(hateoasAlgaLinks
                    .linkToProduto(pedidoDTO.getRestaurante().getId(), itemPedidoDTO.getProduto().getId()));
        });

        return pedidoDTO;
    }
}
