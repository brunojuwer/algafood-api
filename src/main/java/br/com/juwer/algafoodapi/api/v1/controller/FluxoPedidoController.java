package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.openapi.controller.FluxoPedidoControllerOpenApi;
import br.com.juwer.algafoodapi.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/pedidos/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi {

    @Autowired
    private FluxoPedidoService fluxoPedidoService;

    @Override
    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirmacao(@PathVariable String codigo) {
        fluxoPedidoService.confirmar(codigo);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelar(@PathVariable String codigo) {
        fluxoPedidoService.cancelar(codigo);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> entregar(@PathVariable String codigo) {
        fluxoPedidoService.entregar(codigo);

        return ResponseEntity.noContent().build();
    }
}
