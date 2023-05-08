package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.exception.PedidoNaoEncontradoException;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.model.Produto;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.model.Usuario;
import br.com.juwer.algafoodapi.domain.repository.PedidoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRespository pedidoRespository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;


    @Transactional
    public Pedido salvar(Pedido pedido, Long usuario_id) {

        pedido.setSubTotal(BigDecimal.ZERO); // evita null pointer ex

        Long formaPagamentoId = pedido.getFormaPagamento().getId();
        Long restauranteId = pedido.getRestaurante().getId();

        Usuario usuario = cadastroUsuarioService.buscaOuFalha(usuario_id);
        pedido.setCliente(usuario);


        Restaurante restaurante = cadastroRestauranteService.buscaOuFalha(restauranteId);
        pedido.setRestaurante(restaurante);

        pedido.getEnderecoEntrega().setCidade(cadastroCidadeService
                .buscaOuFalha(pedido.getEnderecoEntrega().getCidade().getId()));

        pedido.setFormaPagamento(cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId, restauranteId));

        pedido.getItens().forEach((item) -> {

            Produto produto = cadastroProdutoService.buscaOuFalha(restauranteId, item.getProduto().getId());
            item.setPrecoUnitario(produto.getPreco());
            item.setPrecoTotal(produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
            pedido.setSubTotal(pedido.getSubTotal().add(item.getPrecoTotal()));

            item.setProduto(produto);
            item.setPedido(pedido);
        });

        pedido.setTaxaFrete(restaurante.getTaxaFrete());
        pedido.calcularValorTotal();

        return pedidoRespository.save(pedido);
    }


    public Pedido buscaOuFalha(String codigo) {
        return pedidoRespository.findByCodigo(codigo).orElseThrow(
                () -> new PedidoNaoEncontradoException(codigo)
        );
    }
}
