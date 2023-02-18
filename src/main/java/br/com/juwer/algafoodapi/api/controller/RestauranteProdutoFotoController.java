package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.FotoProdutoDTOAssembler;
import br.com.juwer.algafoodapi.api.model.dto.FotoProdutoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.FotoProdutoDTOInput;
import br.com.juwer.algafoodapi.domain.model.FotoProduto;
import br.com.juwer.algafoodapi.domain.model.Produto;
import br.com.juwer.algafoodapi.domain.service.CadastroProdutoService;
import br.com.juwer.algafoodapi.domain.service.CatalogoFotoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO atualizarFoto(
            @PathVariable Long restauranteId,
            @PathVariable Long produtoId,
            @Valid FotoProdutoDTOInput fotoProdutoDTOInput
            ) throws IOException {

        Produto produto = cadastroProdutoService.buscaOuFalha(restauranteId, produtoId);

        FotoProduto fotoProduto = new FotoProduto();
        MultipartFile file = fotoProdutoDTOInput.getArquivo();

        fotoProduto.setProduto(produto);
        fotoProduto.setDescricao(fotoProdutoDTOInput.getDescricao());
        fotoProduto.setContentType(file.getContentType());
        fotoProduto.setTamanho(file.getSize());
        fotoProduto.setNomeArquivo(file.getOriginalFilename());

        FotoProduto fotoProdutoSalva = catalogoFotoProdutoService.salvar(fotoProduto, file.getInputStream());

        return fotoProdutoDTOAssembler.toModel(fotoProdutoSalva);
    }

    @GetMapping
    public FotoProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId){
        FotoProduto foto = catalogoFotoProdutoService.buscaOuFalha(restauranteId, produtoId);
        return fotoProdutoDTOAssembler.toModel(foto);
    }
}
