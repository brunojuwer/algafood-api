package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.PedidoDTOAssembler;
import br.com.juwer.algafoodapi.api.assembler.PedidoResumoDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.PedidoDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.api.model.dto.PedidoResumoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.pedidosdto.PedidoDTOInput;
import br.com.juwer.algafoodapi.api.openapi.controller.PedidoControllerOpenApi;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.repository.PedidoRespository;
import br.com.juwer.algafoodapi.domain.filter.PedidoFilter;
import br.com.juwer.algafoodapi.domain.service.CadastroPedidoService;
import br.com.juwer.algafoodapi.infrastructure.service.specs.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

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



    @Override
    @GetMapping
    public Page<PedidoResumoDTO> pesquisar(
            PedidoFilter filter,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<Pedido> pedidosPage = pedidoRespository.findAll(PedidoSpecs.usandoFiltro(filter), pageable);
        List<PedidoResumoDTO> pedidoResumoDTOS = pedidoResumoDTOAssembler.toCollectionModel(pedidosPage.getContent());
        return new PageImpl<>(pedidoResumoDTOS, pageable, pedidosPage.getTotalElements());
    }

    @Override
    @GetMapping("/{codigo}")
    public PedidoDTO buscar(@PathVariable String codigo) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(codigo);
        return pedidoDTOAssembler.toModel(pedido);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@RequestBody @Valid PedidoDTOInput pedidoDTOInput) {
        Pedido pedido = pedidoDTODisassembler.toDomainObject(pedidoDTOInput);
        pedido = cadastroPedidoService.salvar(pedido);
        return pedidoDTOAssembler.toModel(pedido);
    }
}
