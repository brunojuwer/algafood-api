package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.ProdutoDTO;
import br.com.juwer.algafoodapi.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDTOAssembler extends GenericAssembler<ProdutoDTO, Produto> {}
