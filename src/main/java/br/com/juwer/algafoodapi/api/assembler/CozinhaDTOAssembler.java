package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDTOAssembler extends GenericAssembler<CozinhaDTO, Cozinha>{}
