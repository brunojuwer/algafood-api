package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.model.dto.EstadoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.EstadoDTOInput;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {


    @ApiOperation("Lista os estados")
    CollectionModel<EstadoDTO> listar();

    @ApiOperation("Busca um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoDTO buscar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);


    @ApiOperation("Cadastra um estado")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Estado cadastrado"),
    })
    EstadoDTO adicionar(@ApiParam(name = "corpo", value = "Representação de um novo estado", required = true)
                        EstadoDTOInput estadoDTOInput);

    @ApiOperation("Atualiza um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Estado atualizado"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    EstadoDTO atualizar(
            @ApiParam(value = "ID de um estado", example = "1", required = true)
            Long estadoId,
            @ApiParam(name = "corpo", value = "Representação de um estado com os novos dados", required = true)
            EstadoDTOInput estadoDTOInput);

    @ApiOperation("Exclui um estado por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Estado excluído"),
            @ApiResponse(code = 404, message = "Estado não encontrado", response = Problem.class)
    })
    void excluir(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId);
}
