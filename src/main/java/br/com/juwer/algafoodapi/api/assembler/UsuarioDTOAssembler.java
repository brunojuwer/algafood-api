package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.UsuarioDTO;
import br.com.juwer.algafoodapi.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTOAssembler extends GenericAssembler<UsuarioDTO, Usuario>{}
