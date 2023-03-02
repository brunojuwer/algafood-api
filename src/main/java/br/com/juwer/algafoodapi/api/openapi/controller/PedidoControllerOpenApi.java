package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.api.model.dto.PedidoResumoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.pedidosdto.PedidoDTOInput;
import br.com.juwer.algafoodapi.domain.filter.PedidoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface PedidoControllerOpenApi {
    @GetMapping
    Page<PedidoResumoDTO> pesquisar(
            PedidoFilter filter,
            @PageableDefault(size = 10) Pageable pageable
    );

    @GetMapping("/{codigo}")
    PedidoDTO buscar(@PathVariable String codigo);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PedidoDTO adicionar(@RequestBody @Valid PedidoDTOInput pedidoDTOInput);
}
