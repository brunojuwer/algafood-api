package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.CidadeDTOInput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation(value = "Listar as cidades")
    List<CidadeDTO> listar();

    @ApiOperation(value = "Buscar uma cidade por ID")
    CidadeDTO buscar(@ApiParam(value = "ID de uma cidade", required = true) Long cidadeId);

    @ApiOperation(value = "Cadastrar uma cidade")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade criada"),

    })
    CidadeDTO adicionar(
            @ApiParam(name = "Corpo", value = "Representação de uma nova cidade")
            CidadeDTOInput cidadeDTOInput);

    @ApiOperation(value = "Atualizar uma cidade por ID")
    CidadeDTO atualizar(
            @ApiParam(value = "ID de uma cidade")
            Long cidadeId,
            @ApiParam(name="corpo", value = "Representação de uma cidade com os novos dados")
            CidadeDTOInput cidadeDTOInput);

    @ApiOperation(value = "Deletar uma cidade por ID")
    void remover(@ApiParam(value = "ID de uma cidade") Long cidadeId);
}
