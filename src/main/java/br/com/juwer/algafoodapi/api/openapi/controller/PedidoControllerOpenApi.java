package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.api.model.dto.PedidoResumoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.pedidosdto.PedidoDTOInput;
import br.com.juwer.algafoodapi.domain.filter.PedidoFilter;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiOperation(value = "Lista os pedidos")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Problema nos filtros", response = Problem.class)
    })
    Page<PedidoResumoDTO> pesquisar(
            PedidoFilter filter,
            Pageable pageable
    );

    @ApiOperation(value = "Busca pedido por código")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class),
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class)
    })
    PedidoDTO buscar(@ApiParam(value = "Código do pedido") String codigo);

    @ApiOperation(value = "Adiciona um novo pedido")
    PedidoDTO adicionar(PedidoDTOInput pedidoDTOInput);
}
