package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.PermissaoDTO;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;

@SecurityRequirement(name = "security_auth")
public interface PermissaoControllerSpringDoc {
    CollectionModel<PermissaoDTO> listar();
}
