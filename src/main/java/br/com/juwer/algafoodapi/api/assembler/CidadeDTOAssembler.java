package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import org.springframework.stereotype.Component;

@Component
public class CidadeDTOAssembler extends GenericAssembler<CidadeDTO, Cidade> {
}
