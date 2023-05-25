package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.FormaPagamentoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "RestauranteFormasPagamento", description = "Gerencia as formas de pagamento de um restaurante")
public interface RestauranteFormasPagamentoControllerSpringDoc {
    CollectionModel<FormaPagamentoDTO> listar(Long restauranteId);

    ResponseEntity<Void> associar(Long restauranteId, Long formaPagamentoId);

    ResponseEntity<Void> desassociar(Long restauranteId, Long formaPagamentoId);
}
