package br.com.juwer.algafoodapi.api.v1.disassembler;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.produtodtos.ProdutoDTOInput;
import br.com.juwer.algafoodapi.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDTODisassembler extends GenericDisassembler<ProdutoDTOInput, Produto>{}
