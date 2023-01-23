package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoDTOAssembler extends GenericAssembler<PedidoDTO, Pedido>{}
