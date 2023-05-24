package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.PedidoResumoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.pedidosdto.PedidoDTOInput;
import br.com.juwer.algafoodapi.domain.filter.PedidoFilter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
public interface PedidoControllerSpringDoc {
    PagedModel<PedidoResumoDTO> pesquisar(
            PedidoFilter filter,
            Pageable pageable
    );

    PedidoDTO buscar(String codigo);

    PedidoDTO adicionar(PedidoDTOInput pedidoDTOInput);
}
