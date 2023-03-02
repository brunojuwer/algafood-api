package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.model.dto.GrupoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.GrupoDTOInput;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation(value = "Lista os grupos")
    List<GrupoDTO> listar();

    @ApiOperation(value = "Busca grupo por ID")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class),
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class)
    })
    GrupoDTO buscar(@ApiParam(value = "Id de um grupo") Long grupoId);

    @ApiOperation(value = "Cadastra um grupo")
    @ApiResponse(code = 201, message = "Grupo criado")
    GrupoDTO adicionar(GrupoDTOInput grupoDTOInput);

    @ApiOperation(value = "Atualiza um grupo por ID")
    GrupoDTO atualizar(@ApiParam(value = "Id de um grupo") Long grupoId, GrupoDTOInput grupoDTOInput);

    @ApiOperation(value = "Exclui um grupo por ID")
    void excluir(@ApiParam(value = "Id de um grupo") Long grupoId);
}
