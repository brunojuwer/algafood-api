package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.FotoProdutoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.FotoProdutoDTOInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;

@SecurityRequirement(name = "security_auth")
@Tag(name = "RestauranteProdutoFoto", description = "Gerencia as fotos de um produto")
public interface RestauranteProdutoFotoControllerSpringDoc {
    FotoProdutoDTO atualizarFoto(
             Long restauranteId,
             Long produtoId,
             FotoProdutoDTOInput fotoProdutoDTOInput
    ) throws IOException;

    void deletar(Long restauranteId, Long produtoId);

    FotoProdutoDTO buscar(Long restauranteId, Long produtoId);

    ResponseEntity<?> servirFoto(Long restauranteId,
                                 Long produtoId,
                                 String acceptMediaTypes) throws HttpMediaTypeNotAcceptableException;


}
