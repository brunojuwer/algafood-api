package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.usuariodtos.UsuarioDTOInput;
import br.com.juwer.algafoodapi.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTODisassembler extends GenericDisassembler<UsuarioDTOInput, Usuario>{}
