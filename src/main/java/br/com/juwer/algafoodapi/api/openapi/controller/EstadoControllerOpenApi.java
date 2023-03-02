package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.model.dto.EstadoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.EstadoDTOInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface EstadoControllerOpenApi {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<EstadoDTO> listar();

    @GetMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    EstadoDTO buscar(@PathVariable Long estadoId);

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    EstadoDTO adicionar(@RequestBody @Valid EstadoDTOInput estadoDTOInput);

    @PutMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    EstadoDTO atualizar(@PathVariable Long estadoId,
                        @RequestBody @Valid EstadoDTOInput estadoDTOInput);

    @DeleteMapping(value = "/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void excluir(@PathVariable Long estadoId);
}
