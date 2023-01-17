package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.CozinhaDTOInput;
import br.com.juwer.algafoodapi.api.model.dto.input.CozinhaIdDTOInput;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDTODisassembler extends GenericDisassembler<CozinhaDTOInput, Cozinha> {}
