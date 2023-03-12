package br.com.juwer.algafoodapi.api.v1.disassembler;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.EstadoDTOInput;
import br.com.juwer.algafoodapi.domain.model.Estado;
import org.springframework.stereotype.Component;

@Component
public class EstadoDTODIsassembler extends GenericDisassembler<EstadoDTOInput, Estado>{}
