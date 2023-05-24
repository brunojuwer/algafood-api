package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.GrupoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_name")
public interface UsuarioGruposControllerSpringDoc {
    CollectionModel<GrupoDTO> listar(Long usuarioId);

    ResponseEntity<Void> associar(Long usuarioId, Long grupoId);

    ResponseEntity<Void> desassociar(Long usuarioId, Long grupoId);
}
