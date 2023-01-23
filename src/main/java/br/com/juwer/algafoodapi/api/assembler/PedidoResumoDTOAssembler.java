package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.PedidoDTO;
import br.com.juwer.algafoodapi.api.model.dto.PedidoResumoDTO;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoResumoDTOAssembler extends GenericAssembler<PedidoResumoDTO, Pedido>{}
