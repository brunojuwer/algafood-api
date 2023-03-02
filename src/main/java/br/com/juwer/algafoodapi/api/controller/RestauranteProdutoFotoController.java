package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.FotoProdutoDTOAssembler;
import br.com.juwer.algafoodapi.api.model.dto.FotoProdutoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.FotoProdutoDTOInput;
import br.com.juwer.algafoodapi.api.openapi.controller.RestauranteProdutoFotoControllerOpenApi;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.model.FotoProduto;
import br.com.juwer.algafoodapi.domain.model.Produto;
import br.com.juwer.algafoodapi.domain.service.CadastroProdutoService;
import br.com.juwer.algafoodapi.domain.service.CatalogoFotoProdutoService;
import br.com.juwer.algafoodapi.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CatalogoFotoProdutoService catalogoFotoProdutoService;

    @Autowired
    private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Override
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

    @Override
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long restauranteId, @PathVariable Long produtoId){
        catalogoFotoProdutoService.deletar(restauranteId, produtoId);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FotoProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId){
        FotoProduto foto = catalogoFotoProdutoService.buscaOuFalha(restauranteId, produtoId);
        return fotoProdutoDTOAssembler.toModel(foto);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId,
                                        @PathVariable Long produtoId,
                                        @RequestHeader(value = "accept") String acceptMediaTypes) throws HttpMediaTypeNotAcceptableException {
        try {
            FotoProduto foto = catalogoFotoProdutoService.buscaOuFalha(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(foto.getContentType());
            List<MediaType> requestedMediaTypes = MediaType.parseMediaTypes(acceptMediaTypes);
            verificarCompatibilidadeMediaType(mediaTypeFoto, requestedMediaTypes);

            FotoStorageService.FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(foto.getNomeArquivo());
            if(fotoRecuperada.temUrl()) {
              return ResponseEntity.status(HttpStatus.FOUND)
                      .header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
                      .build();
            }
            return ResponseEntity.ok()
                    .contentType(mediaTypeFoto)
                    .body(new InputStreamResource(fotoRecuperada.getInputStream()));

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> requestedMediaTypes) throws HttpMediaTypeNotAcceptableException {
        boolean compativel = requestedMediaTypes.stream()
                .anyMatch(mediaType -> mediaType.isCompatibleWith(mediaTypeFoto));

        if(!compativel) {
            throw new HttpMediaTypeNotAcceptableException(requestedMediaTypes);
        }
    }
}
