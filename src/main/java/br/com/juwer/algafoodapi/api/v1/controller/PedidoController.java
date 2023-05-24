package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.PedidoDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.assembler.PedidoResumoDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.disassembler.PedidoDTODisassembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.PedidoResumoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.pedidosdto.PedidoDTOInput;
import br.com.juwer.algafoodapi.api.v1.springdoc.controller.PedidoControllerSpringDoc;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import br.com.juwer.algafoodapi.core.security.SecurityUtils;
import br.com.juwer.algafoodapi.domain.filter.PedidoFilter;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.repository.PedidoRespository;
import br.com.juwer.algafoodapi.domain.service.CadastroPedidoService;
import br.com.juwer.algafoodapi.infrastructure.service.specs.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerSpringDoc {

    @Autowired
    private PedidoRespository pedidoRespository;

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Autowired
    private PedidoDTOAssembler pedidoDTOAssembler;

    @Autowired
    private PedidoResumoDTOAssembler pedidoResumoDTOAssembler;

    @Autowired
    private PedidoDTODisassembler pedidoDTODisassembler;

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    @GetMapping
    @CheckSecurity.Pedidos.PodePesquisar
    public PagedModel<PedidoResumoDTO> pesquisar(
            PedidoFilter filter,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<Pedido> pedidosPage = pedidoRespository.findAll(PedidoSpecs.usandoFiltro(filter), pageable);
        return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoDTOAssembler);
    }

    @Override
    @CheckSecurity.Pedidos.PodeBuscar
    @GetMapping("/{codigo}")
    public PedidoDTO buscar(@PathVariable String codigo) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(codigo);
        return pedidoDTOAssembler.toModel(pedido);
    }

    @Override
    @CheckSecurity.Pedidos.PodeCriar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@RequestBody @Valid PedidoDTOInput pedidoDTOInput) {
        Pedido pedido = pedidoDTODisassembler.toDomainObject(pedidoDTOInput);
        pedido = cadastroPedidoService.salvar(pedido, securityUtils.getUsuarioId());
        return pedidoDTOAssembler.toModel(pedido);
    }
}
