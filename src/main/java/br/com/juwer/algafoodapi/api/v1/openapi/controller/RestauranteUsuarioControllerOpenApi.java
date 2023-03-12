package br.com.juwer.algafoodapi.api.v1.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.v1.model.dto.UsuarioDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioControllerOpenApi {

    @ApiOperation("Listar os usuários de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    CollectionModel<UsuarioDTO> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                            Long restauranteId);

    @ApiOperation("Associar usuário a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado",
                    response = Problem.class)
    })
    ResponseEntity<Void> associar(
        @ApiParam(value = "ID do restaurante", example = "1", required = true)
        Long restauranteId,
        @ApiParam(value = "ID do usuário", example = "1", required = true)
        Long usuarioId);


    @ApiOperation("Desassociar usuário a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado",
                    response = Problem.class)
    })
    ResponseEntity<Void> desassociar(
        @ApiParam(value = "ID do restaurante", example = "1", required = true)
        Long restauranteId,
        @ApiParam(value = "ID do usuário", example = "1", required = true)
        Long usuarioId);
}
