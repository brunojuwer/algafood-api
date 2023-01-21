package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.exception.ProdutoNaoEncontradoException;
import br.com.juwer.algafoodapi.domain.model.Produto;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroProdutoService {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listar(Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscaOuFalha(restauranteId);
        return restaurante.getProdutos();
    }


    @Transactional
    public Produto salvar(Produto produto, Long restauranteId) {
        cadastroRestauranteService.buscaOuFalha(restauranteId);
        produto.setRestaurante(new Restaurante());
        produto.getRestaurante().setId(restauranteId);
        return produtoRepository.save(produto);
    }

    public Produto buscaOuFalha(Long restauranteId, Long produtoId) {
        cadastroRestauranteService.buscaOuFalha(restauranteId);
        return produtoRepository.findProdutoByRestauranteIdAndId(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }

}
