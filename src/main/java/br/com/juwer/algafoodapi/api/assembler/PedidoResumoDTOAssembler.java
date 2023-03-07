package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.controller.PedidoController;
import br.com.juwer.algafoodapi.api.controller.RestauranteController;
import br.com.juwer.algafoodapi.api.controller.UsuarioController;
import br.com.juwer.algafoodapi.api.model.dto.PedidoResumoDTO;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoResumoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoDTOAssembler() {
        super(PedidoController.class, PedidoResumoDTO.class);
    }

    @Override
    public PedidoResumoDTO toModel(Pedido pedido) {
        PedidoResumoDTO pedidoResumoDTO = createModelWithId(pedido.getId(), pedido);
        modelMapper.map(pedido, pedidoResumoDTO);

        pedidoResumoDTO.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedidoResumoDTO.getCliente().getId())).withSelfRel());

        pedidoResumoDTO.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedidoResumoDTO.getRestaurante().getId())).withSelfRel());

        pedidoResumoDTO.add(linkTo(PedidoController.class).withRel("pedidos"));

        return pedidoResumoDTO;
    }

}
