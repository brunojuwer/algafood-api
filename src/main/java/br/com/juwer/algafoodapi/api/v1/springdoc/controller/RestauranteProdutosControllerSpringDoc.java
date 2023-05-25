package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.ProdutoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.produtodtos.ProdutoDTOInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "RestauranteProduto", description = "Gerencia os produtos de um restaurante")
public interface RestauranteProdutosControllerSpringDoc {
    CollectionModel<ProdutoDTO> listar(Long restauranteId,
                                       Boolean incluirInativos);

    ProdutoDTO buscar(Long restauranteId, Long produtoId);

    ProdutoDTO salvar(Long restauranteId, ProdutoDTOInput produtoDTOInput);

    ProdutoDTO atualizar(Long restauranteId, Long produtoId, ProdutoDTOInput produtoDTOInput);
}
