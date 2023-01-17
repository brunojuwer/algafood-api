package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.EstadoDTO;
import br.com.juwer.algafoodapi.domain.model.Estado;
import org.springframework.stereotype.Component;

@Component
public class EstadoDTOAssembler extends GenericAssembler<EstadoDTO, Estado>{}
