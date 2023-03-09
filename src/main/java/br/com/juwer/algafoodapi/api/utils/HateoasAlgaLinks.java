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

    public Link linkToEntregaPedido(String codigo, String rel) {
        return linkTo(methodOn(FluxoPedidoController.class).entregar(codigo)).withRel(rel);
    }

    public Link linkToCancelaPedido(String codigo, String rel) {
        return linkTo(methodOn(FluxoPedidoController.class).cancelar(codigo)).withRel(rel);
    }

    public Link linkToConfirmaPedido(String codigo, String rel) {
        return linkTo(methodOn(FluxoPedidoController.class).confirmacao(codigo)).withRel(rel);
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

    public Link linkToCozinha(Long cozinhaId) {
        return linkTo(methodOn(CozinhaController.class).buscar(cozinhaId)).withSelfRel();
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

    public Link linkToRestauranteUsuarios(Long restauranteId) {
        return linkTo(methodOn(RestauranteUsuarioController.class).listar(restauranteId)).withSelfRel();
    }

    public Link linkToRestauranteUsuariosDesassociar(Long restauranteId, Long usuarioId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioController.class)
                .desassociar(restauranteId, usuarioId)).withRel(rel);
    }

    public Link linkToRestauranteUsuariosAssociar(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteUsuarioController.class)
                .associar(restauranteId, null)).withRel(rel);
    }
    public Link linkToClienteGrupos(Long clienteId) {
        return linkTo(methodOn(UsuarioGruposController.class)
                .listar(clienteId)).withRel("usuario-grupos");
    }

    public Link linkToUsuarioGruposDesassociar(Long usuarioId, Long grupoId, String rel) {
        return linkTo(methodOn(UsuarioGruposController.class).desassociar(usuarioId, grupoId)).withRel(rel);
    }

    public Link linkToUsuarioGruposAssociar(Long usuarioId, String rel) {
        return linkTo(methodOn(UsuarioGruposController.class).associar(usuarioId, null)).withRel(rel);
    }


    public Link linkToRestaurante(Long restauranteId) {
        return linkTo(methodOn(RestauranteController.class)
                .buscar(restauranteId)).withSelfRel();
    }

    public Link linkToRestaurante() {
        return linkTo(methodOn(RestauranteController.class).listar()).withRel("restaurantes");
    }

    public Link listToRestauranteResumo(String rel) {
        TemplateVariables filterVariable = new TemplateVariables(
                new TemplateVariable("projecao", TemplateVariable.VariableType.REQUEST_PARAM)
        );
        String restauranteUrl = linkTo(methodOn(RestauranteController.class)).toUri().toString();
        return Link.of(UriTemplate.of(restauranteUrl, filterVariable), rel);
    }

    public Link linkToFormasPagamentoRestaurante(Long restauranteId) {
        return linkTo(methodOn(RestauranteFormasPagamentoController.class)
                .listar(restauranteId)).withRel("formas-pagamento");
    }

    public Link linkToSelfFormasPagamentorestaurante(Long restauranteId){
        return linkTo(methodOn(RestauranteFormasPagamentoController.class).listar(restauranteId)).withSelfRel();
    }

    public Link linkToFormasPagamentoRestauranteAssociar(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteFormasPagamentoController.class)
                .desassociar(restauranteId, null)).withRel(rel);
    }

    public Link linkToFormasPagamentoRestauranteDesassociar(Long restauranteId, Long formaPagamentoId, String rel) {
        return linkTo(methodOn(RestauranteFormasPagamentoController.class)
                .desassociar(restauranteId, formaPagamentoId)).withRel(rel);
    }

    public Link linkToUsuariosRestaurante(Long restauranteId) {
        return linkTo(methodOn(RestauranteUsuarioController.class)
                .listar(restauranteId)).withRel("responsaveis");
    }

    public Link linkToAtivarOuInativarRestaurante(Long restauranteId, boolean isActive) {
        if(isActive) {
            return linkTo(methodOn(RestauranteController.class).inativar(restauranteId)).withRel("inativar");
        }
        return linkTo(methodOn(RestauranteController.class).ativar(restauranteId)).withRel("ativar");
    }

    public Link linkToAbrirOuFecharRestaurante(Long restauranteId, boolean isOpen) {
        if(isOpen) {
            return linkTo(methodOn(RestauranteController.class).fechar(restauranteId)).withRel("fechar");
        }
        return linkTo(methodOn(RestauranteController.class).abrir(restauranteId)).withRel("abrir");
    }

    public Link linkToProduto(Long restauranteId, Long produtoId) {
        return linkTo(methodOn(RestauranteProdutosController.class)
                .buscar(restauranteId, produtoId)).withSelfRel();
    }

    public Link linkToProduto(Long restauranteId, Long produtoId, String rel) {
        return linkTo(methodOn(RestauranteProdutosController.class)
                .buscar(restauranteId, produtoId)).withRel(rel);
    }

    public Link linkToProduto(Long restauranteId, String rel) {
        return linkTo(methodOn(RestauranteProdutosController.class)
                .listar(restauranteId, null)).withRel(rel);
    }

    public Link linkToFoto(Long restauranteId, Long produtoId, String rel) {
        return linkTo(methodOn(RestauranteProdutoFotoController.class).buscar(restauranteId, produtoId)).withRel(rel);
    }

    public Link linkToGrupos(String rel) {
        return linkTo(methodOn(GrupoController.class).listar()).withRel(rel);
    }

    public Link linkToGrupo(Long grupoId) {
        return linkTo(methodOn(GrupoController.class).buscar(grupoId)).withSelfRel();
    }

    public Link linkToPermissoes(Long grupoId, String rel) {
        return linkTo(methodOn(GrupoPermissaoController.class).listar(grupoId)).withRel(rel);
    }

    public Link linkToPermissoes() {
        return linkTo(PermissaoController.class).withSelfRel();
    }

    public Link linkToGrupoPermissoesAssociar(Long grupoId, String rel){
        return linkTo(methodOn(GrupoPermissaoController.class).associar(grupoId, null)).withRel(rel);
    }

    public Link linkToGrupoPermissoesDesassociar(Long grupoId, Long permissaoId, String rel){
        return linkTo(methodOn(GrupoPermissaoController.class).associar(grupoId, permissaoId)).withRel(rel);
    }

    public Link linkToEstatisticas(String rel) {
        return linkTo(methodOn(EstatisticasController.class).vendaDiariaLink()).withRel(rel);
    }

    public Link linkToVendasDiarias() {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("apartirData", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("ateData", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("timeOffSet", TemplateVariable.VariableType.REQUEST_PARAM)
        );
        String vendasDiariasUrl = linkTo(EstatisticasController.class)
                .slash("vendas-diarias").toUri().toString();

        return Link.of(UriTemplate.of(vendasDiariasUrl, filterVariables), "vendas-diarias");
    }
}
