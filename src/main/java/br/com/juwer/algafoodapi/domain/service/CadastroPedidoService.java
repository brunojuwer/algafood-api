package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.exception.FormaPagamentoNaoEncontrada;
import br.com.juwer.algafoodapi.domain.exception.PedidoNaoEncontradoException;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.model.Usuario;
import br.com.juwer.algafoodapi.domain.repository.PedidoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

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


    @Transactional
    public Pedido salvar(Pedido pedido) {
        Long formaPagamentoId = pedido.getFormaPagamento().getId();
        Long restauranteId = pedido.getRestaurante().getId();

        pedido.setCliente(new Usuario());
        pedido.getCliente().setId(1L);

        Restaurante restaurante = cadastroRestauranteService.buscaOuFalha(restauranteId);
        cadastroCidadeService.buscaOuFalha(pedido.getEnderecoEntrega().getCidade().getId());

        boolean restauranteHaveFormaPagamento = restaurante.getFormasPagamento().stream()
                .anyMatch(item -> Objects.equals(item.getId(), formaPagamentoId));

        if(!restauranteHaveFormaPagamento) {
            throw new FormaPagamentoNaoEncontrada(restauranteId, formaPagamentoId);
        }

        return pedidoRespository.save(pedido);
    }


    public Pedido buscaOuFalha(Long pedidoId) {
        return pedidoRespository.findById(pedidoId).orElseThrow(
                () -> new PedidoNaoEncontradoException(pedidoId)
        );
    }
}
