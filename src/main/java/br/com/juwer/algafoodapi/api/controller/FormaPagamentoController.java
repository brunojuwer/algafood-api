package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.FormaPagamentoDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.FormaPagamentoDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.FormaPagamentoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.FormaPagamentoDTOInput;
import br.com.juwer.algafoodapi.domain.model.FormaPagamento;
import br.com.juwer.algafoodapi.domain.repository.FormaPagamentoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {


    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @Autowired
    private FormaPagamentoDTODisassembler  formaPagamentoDTODisassembler;

    @GetMapping
    public List<FormaPagamentoDTO> listar(){
        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAll();
        return formaPagamentoDTOAssembler.toCollectionModel(formaPagamentos);
    }

    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoDTO buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = cadastroFormaPagamentoService
                .buscarOuFalhar(formaPagamentoId);

        return formaPagamentoDTOAssembler.toModel(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoDTOInput formaPagamentoDTOInput) {
        FormaPagamento formaPagamento = formaPagamentoDTODisassembler
                .toDomainObject(formaPagamentoDTOInput);

        return formaPagamentoDTOAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamento));
    }

    @PutMapping("/{formaPagamentoID}")
    public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoID,
                                       @RequestBody @Valid FormaPagamentoDTOInput formaPagamentoDTOInput) {
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoID);
        formaPagamentoDTODisassembler.copyToDomainObject(formaPagamentoDTOInput, formaPagamento);
        return formaPagamentoDTOAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamento));
    }

    @DeleteMapping("/{formaPagamentoID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long formaPagamentoID){
        cadastroFormaPagamentoService.excluir(formaPagamentoID);
    }
}
