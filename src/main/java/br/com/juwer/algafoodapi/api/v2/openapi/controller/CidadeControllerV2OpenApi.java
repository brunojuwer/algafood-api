package br.com.juwer.algafoodapi.api.v2.openapi.controller;

import br.com.juwer.algafoodapi.api.v2.model.dto.CidadeDTOV2;
import br.com.juwer.algafoodapi.api.v2.model.dtoinput.CidadeDTOInputV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Cidades")
public interface CidadeControllerV2OpenApi {

    @ApiOperation("Listar todas as Cidades")
    CollectionModel<CidadeDTOV2> listar();

    @ApiOperation("Buscar cidade por Id")
    CidadeDTOV2 buscar(Long cidadeId);

    @ApiOperation("Adicionar uma cidade")
    CidadeDTOV2 adicionar(CidadeDTOInputV2 cidadeDTOInput);

    @ApiOperation("Atualizar cidade por Id")
    CidadeDTOV2 atualizar(Long cidadeId, CidadeDTOInputV2 cidadeDTOInput);
}
