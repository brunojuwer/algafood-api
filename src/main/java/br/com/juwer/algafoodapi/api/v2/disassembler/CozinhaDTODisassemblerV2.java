package br.com.juwer.algafoodapi.api.v2.disassembler;

import br.com.juwer.algafoodapi.api.v2.model.dtoinput.CozinhaDTOInputV2;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDTODisassemblerV2 extends GenericDisassemblerV2<CozinhaDTOInputV2, Cozinha> {}
