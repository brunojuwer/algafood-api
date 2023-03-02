package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.model.dto.UsuarioDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioControllerOpenApi {

    @ApiOperation("Listar os usuários de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    List<UsuarioDTO> listar(@ApiParam(value = "ID do restaurante", example = "1", required = true)
                            Long restauranteId);

    @ApiOperation("Associar usuário a um restaurante")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado",
                    response = Problem.class)
    })
    void associar(
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
    void desaassociar(
        @ApiParam(value = "ID do restaurante", example = "1", required = true)
        Long restauranteId,
        @ApiParam(value = "ID do usuário", example = "1", required = true)
        Long usuarioId);
}
