package br.com.juwer.algafoodapi.api.v2.springdoc.controller;

import br.com.juwer.algafoodapi.api.v2.model.dto.CidadeDTOV2;
import br.com.juwer.algafoodapi.api.v2.model.dtoinput.CidadeDTOInputV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "CidadeController", description = "Gerencia as cidades")
public interface CidadeControllerV2SpringDoc {

    @Operation(summary = "Lista todas as cidades")
    CollectionModel<CidadeDTOV2> listar();

    @Operation(summary = "Busca uma cidade por ID")
    CidadeDTOV2 buscar(Long cidadeId);

    @Operation(summary = "Adiciona uma nova cidade")
    CidadeDTOV2 adicionar(CidadeDTOInputV2 cidadeDTOInput);

    @Operation(summary = "Atualiza uma cidade")
    CidadeDTOV2 atualizar(Long cidadeId, CidadeDTOInputV2 cidadeDTOInput);

    @Operation(summary = "Remove uma cidade")
    void remover(Long cidadeId);
}
