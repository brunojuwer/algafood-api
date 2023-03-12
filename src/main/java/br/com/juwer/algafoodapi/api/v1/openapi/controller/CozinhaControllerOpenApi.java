package br.com.juwer.algafoodapi.api.v1.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.CozinhaDTOInput;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @ApiOperation(value = "Listar todas as cozinhas (Com páginação)")
    PagedModel<CozinhaDTO> listar(Pageable pageable);

    @ApiOperation(value = "Buscar cozinha por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Id da cozinha inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class)
    })
    CozinhaDTO buscar(@ApiParam(value = "ID de uma cozinha") Long cozinhaId);

    @ApiOperation(value = "Cadastrar uma cozinha")
    CozinhaDTO adicionar(CozinhaDTOInput cozinhaIdDTOInput);

    @ApiOperation(value = "Atualizar cozinha por ID")
    CozinhaDTO atualizar(@ApiParam(value = "ID de uma cozinha") Long cozinhaId, CozinhaDTOInput cozinha);

    @ApiOperation(value = "Excluir cozinha por ID")
    void remover(@ApiParam(value = "ID de uma cozinha") Long cozinhaId);
}
