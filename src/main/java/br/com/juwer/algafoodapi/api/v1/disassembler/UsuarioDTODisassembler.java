package br.com.juwer.algafoodapi.api.v1.disassembler;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos.UsuarioDTOInput;
import br.com.juwer.algafoodapi.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTODisassembler extends GenericDisassembler<UsuarioDTOInput, Usuario>{}
