package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.CidadeDTOInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidade", description = "Gerencia as Cidades")
public interface CidadeControllerSpringDoc {
    @Deprecated
    @Operation(summary = "Lista todas as cidades")
    CollectionModel<CidadeDTO> listar();

    @Operation(summary = "Busca uma cidade por ID")
    CidadeDTO buscar(
        @Parameter(description = "ID de uma cidade", example = "1", required = true)
        Long cidadeId);

    @Operation(summary = "Adiciona uma nova cidade")
    CidadeDTO adicionar(
        @RequestBody(description = "Representação de uma nova cidade" , required = true)
        CidadeDTOInput cidadeDTOInput);

    @Operation(summary = "Atualiza uma cidade")
    CidadeDTO atualizar(
        @Parameter(description = "ID de uma cidade", example = "1", required = true)
        Long cidadeId,
        @RequestBody(description = "Representação de uma nova cidade" , required = true)
        CidadeDTOInput cidadeDTOInput);

    @Operation(summary = "Remove uma cidade")
    void remover(
        @Parameter(description = "ID de uma cidade", example = "1", required = true)
        Long cidadeId);
}
