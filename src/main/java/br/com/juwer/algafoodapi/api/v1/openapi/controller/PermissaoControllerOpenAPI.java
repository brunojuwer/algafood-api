package br.com.juwer.algafoodapi.api.v1.openapi.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.PermissaoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenAPI {

    @ApiOperation("Listar todas as permissões")
    @GetMapping
    CollectionModel<PermissaoDTO> listar();
}