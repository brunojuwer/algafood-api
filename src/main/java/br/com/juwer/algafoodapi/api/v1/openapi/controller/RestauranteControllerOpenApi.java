package br.com.juwer.algafoodapi.api.v1.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.restaurantedtos.RestauranteDTOInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteBasicoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteResumoDTO;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

    @ApiOperation(value = "Listar Restaurantes")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome",
                    name = "projecao", paramType = "query", dataType = "java.lang.String")
    })
    CollectionModel<RestauranteBasicoDTO> listar();

    @ApiIgnore
    @ApiOperation(value = "Listar Restaurantes", hidden = true)
    CollectionModel<RestauranteResumoDTO> listarApenasNomes();


    @ApiOperation(value = "Busca restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    RestauranteDTO buscar(@ApiParam(value = "ID de restaurante", required = true) Long restauranteId);

    @ApiOperation(value = "Cadastro de um restaurante")
    RestauranteDTO adicionar(RestauranteDTOInput restauranteDTOInput);

    @ApiOperation("Atualiza um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Restaurante atualizado"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    RestauranteDTO atualizar(@ApiParam(value = "ID de restaurante", required = true)  Long restauranteId, RestauranteDTOInput restauranteDTOInput);

    @ApiOperation(value = "Deletar um restaurante por ID")
    void excluir(@ApiParam(value = "ID de restaurante", required = true)  Long restauranteId);

    @ApiOperation("Ativa um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> ativar(@ApiParam(value = "ID de restaurantes", required = true) Long restauranteId);

    @ApiOperation("Inativa um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> inativar(@ApiParam(value = "Id de restaurante", required = true)  Long restauranteId);

    @ApiOperation("Ativa múltiplos restaurantes")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
    void ativarMultiplos( List<Long> restauranteIds);

    @ApiOperation("Inativa múltiplos restaurantes")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurantes ativados com sucesso")
    })
    void inativarMultiplos(@ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) List<Long> restauranteIds);

    @ApiOperation("Abre um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> abrir(@ApiParam(name = "corpo", value = "ID de restaurante", required = true) Long restauranteId);

    @ApiOperation("Fecha um restaurante por ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
            @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
    ResponseEntity<Void> fechar(@ApiParam(value = "Id do restaurante", required = true) Long restauranteId);
}
