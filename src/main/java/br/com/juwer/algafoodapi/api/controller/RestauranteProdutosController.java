package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.ProdutoDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.ProdutoDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.ProdutoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.ProdutoDTOInput;
import br.com.juwer.algafoodapi.domain.exception.ProdutoNaoEncontradoException;
import br.com.juwer.algafoodapi.domain.model.Produto;
import br.com.juwer.algafoodapi.domain.repository.ProdutoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroProdutoService;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private ProdutoDTOAssembler produtoDTOAssembler;

    @Autowired
    private ProdutoDTODisassembler produtoDTODisassembler;

    @GetMapping
    public List<ProdutoDTO> listar(@PathVariable Long restauranteId){
        List<Produto> produtos = cadastroProdutoService.listar(restauranteId);
        return produtoDTOAssembler.toCollectionModel(produtos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
            cadastroRestauranteService.buscaOuFalha(restauranteId);
        try {
            Produto produto = produtoRepository.findProdutoByRestauranteIdAndId(restauranteId, produtoId);
            return produtoDTOAssembler.toModel(produto);
        } catch (IllegalArgumentException e) {
            throw new ProdutoNaoEncontradoException(restauranteId, produtoId);
        }

    }

    @PostMapping
    public ProdutoDTO salvar(@PathVariable Long restauranteId, @RequestBody ProdutoDTOInput produtoDTOInput) {
        Produto produto = produtoDTODisassembler.toDomainObject(produtoDTOInput);
        return produtoDTOAssembler.toModel(cadastroProdutoService.salvar(produto, restauranteId));
    }
}
