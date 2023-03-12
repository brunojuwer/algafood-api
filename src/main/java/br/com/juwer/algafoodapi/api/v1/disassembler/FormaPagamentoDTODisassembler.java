package br.com.juwer.algafoodapi.api.v1.disassembler;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.formapagamentodtos.FormaPagamentoDTOInput;
import br.com.juwer.algafoodapi.domain.model.FormaPagamento;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoDTODisassembler extends GenericDisassembler<FormaPagamentoDTOInput, FormaPagamento>{}
