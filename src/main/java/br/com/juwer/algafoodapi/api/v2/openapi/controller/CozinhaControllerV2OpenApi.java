package br.com.juwer.algafoodapi.api.v2.openapi.controller;

import br.com.juwer.algafoodapi.api.v2.model.dto.CozinhaDTOV2;
import br.com.juwer.algafoodapi.api.v2.model.dtoinput.CozinhaDTOInputV2;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Cozinhas")
public interface CozinhaControllerV2OpenApi {

    @ApiOperation("Listar todas as Cozinhas")
    PagedModel<CozinhaDTOV2> listar(Pageable pageable);

    @ApiOperation("Buscar cozinha por ID")
    CozinhaDTOV2 buscar( Long cozinhaId);

    @ApiOperation("Adicionar uma nova cozinha")
    CozinhaDTOV2 adicionar(CozinhaDTOInputV2 cozinhaIdDTOInput);

    @ApiOperation("Atualizar cozinha por ID")
    CozinhaDTOV2 atualizar( Long cozinhaId, CozinhaDTOInputV2 cozinha);

    @ApiOperation("Remover cozinha por ID")
    void remover( Long cozinhaId);
}
