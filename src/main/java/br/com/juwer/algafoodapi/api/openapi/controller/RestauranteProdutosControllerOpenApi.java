package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.model.dto.ProdutoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.produtodtos.ProdutoDTOInput;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Produtos")
public interface RestauranteProdutosControllerOpenApi {


    @ApiOperation("Lista os produtos de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    List<ProdutoDTO> listar(
        @ApiParam(value = "ID do restaurante", example = "1", required = true)
        Long restauranteId,
        @ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem",
                example = "false", defaultValue = "false")
        boolean incluirInativos);

    @ApiOperation("Busca um produto de um restaurante")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do restaurante ou produto inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
    })
    ProdutoDTO buscar(
        @ApiParam(value = "ID do restaurante", example = "1", required = true)
        Long restauranteId,
        @ApiParam(value = "ID do produto", example = "1", required = true)
        Long produtoId);

    @ApiOperation("Cadastra um produto de um restaurante")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Produto cadastrado"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ProdutoDTO salvar(
        @ApiParam(value = "ID do restaurante", example = "1", required = true)
        Long restauranteId,
        @ApiParam(name = "corpo", value = "Representação de um novo produto", required = true)
        ProdutoDTOInput produtoDTOInput);

    @ApiOperation("Atualiza um produto de um restaurante")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto atualizado"),
            @ApiResponse(code = 404, message = "Produto de restaurante não encontrado", response = Problem.class)
    })
    ProdutoDTO atualizar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true)
            Long restauranteId,
            @ApiParam(value = "ID do produto", example = "1", required = true)
            Long produtoId,
            @ApiParam(name = "corpo", value = "Representação de um produto com os novos dados",
                    required = true)
            ProdutoDTOInput produtoDTOInput);
}
