package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.FormaPagamentoDTOInput;
import br.com.juwer.algafoodapi.domain.model.FormaPagamento;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoDTODisassembler extends GenericDisassembler<FormaPagamentoDTOInput, FormaPagamento>{}
