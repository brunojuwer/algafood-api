package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.ProdutoDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.ProdutoDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.ProdutoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.produtodtos.ProdutoDTOInput;
import br.com.juwer.algafoodapi.api.openapi.controller.RestauranteProdutosControllerOpenApi;
import br.com.juwer.algafoodapi.domain.model.Produto;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.ProdutoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroProdutoService;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutosController implements RestauranteProdutosControllerOpenApi {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private ProdutoDTOAssembler produtoDTOAssembler;

    @Autowired
    private ProdutoDTODisassembler produtoDTODisassembler;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;


    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProdutoDTO> listar(@PathVariable Long restauranteId, @RequestParam(required = false) boolean incluirInativos){
        Restaurante restaurante = cadastroRestauranteService.buscaOuFalha(restauranteId);
        List<Produto> produtos = null;

        if(incluirInativos) {
            produtos = cadastroProdutoService.listar(restauranteId);
            return produtoDTOAssembler.toCollectionModel(produtos);
        }

        produtos = produtoRepository.findAtivosByRestauranteId(restaurante);
        return produtoDTOAssembler.toCollectionModel(produtos);
    }

    @Override
    @GetMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
            Produto produto = cadastroProdutoService.buscaOuFalha(restauranteId, produtoId);
            return produtoDTOAssembler.toModel(produto);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoDTO salvar(@PathVariable Long restauranteId, @Valid @RequestBody ProdutoDTOInput produtoDTOInput) {
        Produto produto = produtoDTODisassembler.toDomainObject(produtoDTOInput);
        return produtoDTOAssembler.toModel(cadastroProdutoService.salvar(produto, restauranteId));
    }

    @Override
    @PutMapping(path = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                @Valid @RequestBody ProdutoDTOInput produtoDTOInput) {

            Produto produto = cadastroProdutoService.buscaOuFalha(restauranteId, produtoId);
            produtoDTODisassembler.copyToDomainObject(produtoDTOInput, produto);
            return produtoDTOAssembler.toModel(cadastroProdutoService.salvar(produto, restauranteId));
    }
}
