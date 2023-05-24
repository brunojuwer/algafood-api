package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.PermissaoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface GrupoPermissaoControllerSpringDoc {
    CollectionModel<PermissaoDTO> listar(Long grupoId);

    ResponseEntity<Void> associar(Long grupoId, Long permissaoId);

    ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId);
}
