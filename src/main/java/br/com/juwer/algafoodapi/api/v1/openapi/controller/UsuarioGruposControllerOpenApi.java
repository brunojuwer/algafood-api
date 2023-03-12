package br.com.juwer.algafoodapi.api.v1.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.v1.model.dto.GrupoDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Usuários")
public interface UsuarioGruposControllerOpenApi {

    @ApiOperation("Listar grupos pelo ID do usuário")
    CollectionModel<GrupoDTO> listar(@ApiParam(value = "ID do usuário") Long usuarioId);

    @ApiOperation("Associão de grupo com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado",
                    response = Problem.class)
    })
    ResponseEntity<Void> associar(@ApiParam(value = "ID do usuário") Long usuarioId,
                                  @ApiParam(value = "ID do grupo") Long grupoId);


    @ApiOperation("Desassociação de grupo com usuário")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
            @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado",
                    response = Problem.class)
    })
    ResponseEntity<Void> desassociar(@ApiParam(value = "ID do usuário") Long usuarioId, @ApiParam(value = "ID do grupo") Long grupoId);
}
