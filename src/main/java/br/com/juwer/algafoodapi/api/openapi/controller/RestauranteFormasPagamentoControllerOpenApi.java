package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.model.dto.FormaPagamentoDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteFormasPagamentoControllerOpenApi {

    @ApiOperation("Listar as formas de pagamento de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    CollectionModel<FormaPagamentoDTO> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                                   Long restauranteId);

    @ApiOperation("Associar forma de pagamento a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado",
                    response = Problem.class)
    })
    ResponseEntity<Void> associar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                  Long restauranteId,
                  @ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
                  Long formaPagamentoId);

    @ApiOperation("Desassociar forma de pagamento a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado",
                    response = Problem.class)
    })
    ResponseEntity<Void> desassociar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                     Long restauranteId,
                                     @ApiParam(value = "ID da forma de pagamento", example = "1", required = true)
                     Long formaPagamentoId);
}
