package br.com.juwer.algafoodapi.api.v2.springdoc.controller;

import br.com.juwer.algafoodapi.api.v2.model.dto.CozinhaDTOV2;
import br.com.juwer.algafoodapi.api.v2.model.dtoinput.CozinhaDTOInputV2;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "CozinhaController", description = "Gerencia as cozinhas")
public interface CozinhaControllerV2SpringDoc {
    PagedModel<CozinhaDTOV2> listar(Pageable pageable);

    CozinhaDTOV2 buscar(Long cozinhaId);

    CozinhaDTOV2 adicionar(CozinhaDTOInputV2 cozinhaIdDTOInput);

    CozinhaDTOV2 atualizar(Long cozinhaId, CozinhaDTOInputV2 cozinha);

    void remover(Long cozinhaId);
}
