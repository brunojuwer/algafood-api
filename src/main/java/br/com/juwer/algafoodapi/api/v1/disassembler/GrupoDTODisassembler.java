package br.com.juwer.algafoodapi.api.v1.disassembler;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.GrupoDTOInput;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import org.springframework.stereotype.Component;

@Component
public class GrupoDTODisassembler extends GenericDisassembler<GrupoDTOInput, Grupo> {}
