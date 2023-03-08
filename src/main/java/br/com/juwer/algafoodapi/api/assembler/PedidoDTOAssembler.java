package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.controller.*;
import br.com.juwer.algafoodapi.api.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PedidoDTOAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDTOAssembler() {
        super(PedidoController.class, PedidoDTO.class);
    }

    @Override
    public PedidoDTO toModel(Pedido pedido) {
        PedidoDTO pedidoDTO = modelMapper.map(pedido, PedidoDTO.class);

        pedidoDTO.add(linkTo(methodOn(PedidoController.class).buscar(pedido.getCodigo())).withSelfRel());

        TemplateVariables pageVariables = new TemplateVariables(
                new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
        );
        String pedidosUrl = linkTo(PedidoController.class).toUri().toString();
        pedidoDTO.add(Link.of(UriTemplate.of(pedidosUrl, pageVariables), "pedidos"));

        pedidoDTO.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
                .buscar(pedidoDTO.getEnderecoEntrega().getCidade().getId())).withSelfRel());

        pedidoDTO.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class)
                .buscar(pedidoDTO.getFormaPagamento().getId())).withSelfRel());

        pedidoDTO.getCliente().add(linkTo(methodOn(UsuarioController.class)
                .buscar(pedidoDTO.getCliente().getId())).withSelfRel());

        pedidoDTO.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
                .buscar(pedidoDTO.getRestaurante().getId())).withSelfRel());

        pedidoDTO.getItens().forEach(itemPedidoDTO -> {
            itemPedidoDTO.getProduto().add(linkTo(methodOn(RestauranteProdutosController.class)
                    .buscar(pedidoDTO.getRestaurante().getId(), itemPedidoDTO.getProduto().getId())).withSelfRel());
        });

        return pedidoDTO;
    }
}
