package br.com.juwer.algafoodapi.api.v1.assembler;

import br.com.juwer.algafoodapi.api.v1.controller.PedidoController;
import br.com.juwer.algafoodapi.api.v1.model.dto.PedidoResumoDTO;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class PedidoResumoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public PedidoResumoDTOAssembler() {
        super(PedidoController.class, PedidoResumoDTO.class);
    }

    @Override
    public PedidoResumoDTO toModel(Pedido pedido) {
        PedidoResumoDTO pedidoResumoDTO = this.modelMapper.map(pedido, PedidoResumoDTO.class);

        pedidoResumoDTO.add(hateoasAlgaLinks.linkToPedidos(pedidoResumoDTO.getCodigo()));

        pedidoResumoDTO.getCliente().add(hateoasAlgaLinks.linkToCliente(pedidoResumoDTO.getCliente().getId()));

        pedidoResumoDTO.getRestaurante()
                .add(hateoasAlgaLinks.linkToRestaurante(pedidoResumoDTO.getRestaurante().getId()));

        return pedidoResumoDTO;
    }
}
