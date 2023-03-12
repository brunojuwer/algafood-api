package br.com.juwer.algafoodapi.api.v1.disassembler;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.CozinhaDTOInput;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDTODisassembler extends GenericDisassembler<CozinhaDTOInput, Cozinha> {}
