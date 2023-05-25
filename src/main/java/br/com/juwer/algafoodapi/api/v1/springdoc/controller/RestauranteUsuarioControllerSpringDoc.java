package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "RestauranteUsuario", description = "Gerencia os usuários de um restaurante")
public interface RestauranteUsuarioControllerSpringDoc {
    CollectionModel<UsuarioDTO> listar(Long restauranteId);

    ResponseEntity<Void> associar(Long restauranteId, Long usuarioId);

    ResponseEntity<Void> desassociar(Long restauranteId, Long usuarioId);
}
