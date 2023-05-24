package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface FluxoPedidoControllerSpringDoc {

    ResponseEntity<Void> confirmacao(String codigo);

    ResponseEntity<Void> cancelar(String codigo);

    ResponseEntity<Void> entregar(String codigo);
}
