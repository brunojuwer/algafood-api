package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.PedidoDTOAssembler;
import br.com.juwer.algafoodapi.api.assembler.PedidoResumoDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.PedidoDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.api.model.dto.PedidoResumoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.pedidosdto.PedidoDTOInput;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.repository.PedidoRespository;
import br.com.juwer.algafoodapi.domain.repository.filter.PedidoFilter;
import br.com.juwer.algafoodapi.domain.service.CadastroPedidoService;
import br.com.juwer.algafoodapi.infrastructure.specs.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

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



    @GetMapping
    public List<PedidoResumoDTO> pesquisar(PedidoFilter filter) {
        List<Pedido> pedidos = pedidoRespository.findAll(PedidoSpecs.usandoFiltro(filter));
        return pedidoResumoDTOAssembler.toCollectionModel(pedidos);
    }

//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = pedidoRespository.findAll();
//        List<PedidoResumoDTO> pedidosResumoDTO = pedidoResumoDTOAssembler.toCollectionModel(pedidos);
//
//        MappingJacksonValue pedidosFilter = new MappingJacksonValue(pedidosResumoDTO);
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//
//
//        if(StringUtils.isNotBlank(campos)) {
//            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//
//        pedidosFilter.setFilters(filterProvider);
//        return pedidosFilter;
//    }

    @GetMapping("/{codigo}")
    public PedidoDTO buscar(@PathVariable String codigo) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(codigo);
        return pedidoDTOAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@RequestBody @Valid PedidoDTOInput pedidoDTOInput) {
        Pedido pedido = pedidoDTODisassembler.toDomainObject(pedidoDTOInput);
        pedido = cadastroPedidoService.salvar(pedido);
        return pedidoDTOAssembler.toModel(pedido);
    }
}
