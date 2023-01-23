package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.PedidoDTOAssembler;
import br.com.juwer.algafoodapi.api.assembler.PedidoResumoDTOAssembler;
import br.com.juwer.algafoodapi.api.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.api.model.dto.PedidoResumoDTO;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.repository.PedidoRespository;
import br.com.juwer.algafoodapi.domain.service.CadastroPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public List<PedidoResumoDTO> listar() {
        List<Pedido> pedidos = pedidoRespository.findAll();
        return pedidoResumoDTOAssembler.toCollectionModel(pedidos);
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO buscar(@PathVariable Long pedidoId) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(pedidoId);
        return pedidoDTOAssembler.toModel(pedido);
    }
}
