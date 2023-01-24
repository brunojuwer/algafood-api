package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.produtodtos.ProdutoDTOInput;
import br.com.juwer.algafoodapi.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDTODisassembler extends GenericDisassembler<ProdutoDTOInput, Produto>{}
