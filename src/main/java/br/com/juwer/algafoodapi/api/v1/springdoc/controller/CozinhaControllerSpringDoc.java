package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.CozinhaDTOInput;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinha", description = "Gerencia as cozinhas")
public interface CozinhaControllerSpringDoc {

    @Operation(summary = "Lista todas as cozinhas")
    PagedModel<CozinhaDTO> listar(
        @RequestBody(description = "Paginação da lista")
        Pageable pageable);

    @Operation(summary = "Busca uma cozinha por ID")
    CozinhaDTO buscar(
        @Parameter(description = "ID de uma cozinha", example = "1", required = true)
        Long cozinhaId);

    @Operation(summary = "Adiciona uma nova cozinha")
    CozinhaDTO adicionar(
        @RequestBody(description = "Representação de uma nova cozinha" , required = true)
        CozinhaDTOInput cozinhaIdDTOInput);

    @Operation(summary = "Atualiza uma cozinha")
    CozinhaDTO atualizar(
        @Parameter(description = "ID de uma cozinha", example = "1", required = true)
        Long cozinhaId,
        @RequestBody(description = "Representação de uma nova cozinha" , required = true)
        CozinhaDTOInput cozinha);

    @Operation(summary = "Remove uma cozinha")
    void remover(
        @Parameter(description = "ID de uma cozinha", example = "1", required = true)
        Long cozinhaId);
}
