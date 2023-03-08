package br.com.juwer.algafoodapi.api.utils;

import br.com.juwer.algafoodapi.api.controller.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HateoasAlgaLinks {

    public static final TemplateVariables pageVariables = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
    );

    public Link linkToPedidos() {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("clienteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("apartirData", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("ateData", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String pedidosUrl = linkTo(PedidoController.class).toUri().toString();
        return Link.of(UriTemplate.of(pedidosUrl, pageVariables.concat(filterVariables)), "pedidos");
    }

    public Link linkToPedidos(String codigo) {
        return linkTo(methodOn(PedidoController.class).buscar(codigo)).withSelfRel();
    }

    public Link linkToCidade() {
        return linkTo(methodOn(CidadeController.class).listar())
                .withRel("cidades");
    }

    public Link linktoSelfCidades() {
        return linkTo(CidadeController.class).withSelfRel();
    }

    public Link linkToCidade(Long cidadeId) {
        return linkTo(methodOn(CidadeController.class)
                .buscar(cidadeId)).withSelfRel();
    }

    public Link linkToCozinha() {
        String cozinhasUrl = linkTo(CozinhaController.class).toUri().toString();
        return Link.of(UriTemplate.of(cozinhasUrl, pageVariables) , "cozinhas");
    }

    public Link linkToSelfEstados() {
        return linkTo((EstadoController.class)).withSelfRel();
    }
    public Link linkToEstados() {
        return linkTo(methodOn(EstadoController.class)
                .listar()).withRel("estados");
    }
    public Link linktoEstado(Long estadoId) {
        return linkTo(methodOn(EstadoController.class)
                .buscar(estadoId))
                .withSelfRel();
    }

    public Link linkToFormaPagamento(Long formaPagamentoId) {
       return linkTo(methodOn(FormaPagamentoController.class)
                .buscar(formaPagamentoId)).withSelfRel();
    }

    public Link linkToFormasPagamento(boolean isSelfRef) {
        if(isSelfRef) {
            return linkTo(FormaPagamentoController.class).withSelfRel();
        }

        return linkTo(FormaPagamentoController.class).withRel("formasPagamento");
    }

    public Link linkToCliente(Long clienteId) {
        return linkTo(methodOn(UsuarioController.class)
                .buscar(clienteId)).withSelfRel();
    }

    public Link linkToClientes(boolean isSelfRel) {
        if(isSelfRel) {
            return linkTo(UsuarioController.class).withSelfRel();
        }
        return linkTo(methodOn(UsuarioController.class).listar()).withRel("usuarios");
    }

    public Link linkToClienteGrupos(Long clienteId) {
        return linkTo(methodOn(UsuarioGruposController.class)
                .listar(clienteId)).withRel("usuario-grupos");
    }

    public Link linkToRestaurante(Long restauranteId) {
        return linkTo(methodOn(RestauranteController.class)
                .buscar(restauranteId)).withSelfRel();
    }

    public Link linkToProduto(Long restauranteId, Long produtoId) {
        return linkTo(methodOn(RestauranteProdutosController.class)
                .buscar(restauranteId, produtoId)).withSelfRel();
    }

}
