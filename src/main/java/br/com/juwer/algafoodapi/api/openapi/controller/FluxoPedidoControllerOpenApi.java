package br.com.juwer.algafoodapi.api.openapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

    @ApiOperation("Confirmar um pedido")
    ResponseEntity<Void> confirmacao(
        @ApiParam(value = "Código do pedido", required = true, example = "51aefec7-31a7-475d-86ba-c5969445c235")
        String codigo
    );

    @ApiOperation("Cancelar um pedido")
    ResponseEntity<Void> cancelar(
        @ApiParam(value = "Código do pedido", required = true, example = "51aefec7-31a7-475d-86ba-c5969445c235")
        String codigo
    );

    @ApiOperation("Entregar um pedido")
    ResponseEntity<Void> entregar(
        @ApiParam(value = "Código do pedido", required = true, example = "51aefec7-31a7-475d-86ba-c5969445c235")
        String codigo
    );
}
