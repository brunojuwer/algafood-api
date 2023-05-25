package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.CozinhaDTOInput;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
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

    PagedModel<CozinhaDTO> listar(Pageable pageable);

    CozinhaDTO buscar(Long cozinhaId);

    CozinhaDTO adicionar(CozinhaDTOInput cozinhaIdDTOInput);

    CozinhaDTO atualizar(Long cozinhaId, CozinhaDTOInput cozinha);

    void remover(Long cozinhaId);
}
