package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.CozinhaDTOInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface CozinhaControllerOpenApi {
    @GetMapping()
    Page<CozinhaDTO> listar(@PageableDefault(size = 10) Pageable pageable);

    @GetMapping("/{cozinhaId}")
    CozinhaDTO buscar(@PathVariable Long cozinhaId);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CozinhaDTO adicionar(@RequestBody @Valid CozinhaDTOInput cozinhaIdDTOInput);

    @PutMapping("/{cozinhaId}")
    CozinhaDTO atualizar(@PathVariable Long cozinhaId,
                         @RequestBody @Valid CozinhaDTOInput cozinha);

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void remover(@PathVariable Long cozinhaId);
}
