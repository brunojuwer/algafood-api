package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.FotoProdutoDTO;
import br.com.juwer.algafoodapi.domain.model.FotoProduto;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoDTOAssembler extends GenericAssembler<FotoProdutoDTO, FotoProduto>{}
