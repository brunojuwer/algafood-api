package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.PermissaoDTO;
import br.com.juwer.algafoodapi.domain.model.Permissao;
import org.springframework.stereotype.Component;

@Component
public class PermissoesDTOAssembler extends GenericAssembler<PermissaoDTO, Permissao> {}
