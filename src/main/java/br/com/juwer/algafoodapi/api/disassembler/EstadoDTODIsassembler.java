package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.EstadoDTOInput;
import br.com.juwer.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoDTODIsassembler extends GenericDisassembler<EstadoDTOInput, Estado>{}
