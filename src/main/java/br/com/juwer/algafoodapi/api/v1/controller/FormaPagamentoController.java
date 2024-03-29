package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.FormaPagamentoDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.disassembler.FormaPagamentoDTODisassembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.FormaPagamentoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.formapagamentodtos.FormaPagamentoDTOInput;
import br.com.juwer.algafoodapi.api.v1.springdoc.controller.FormaPagamentoControllerSpringDoc;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import br.com.juwer.algafoodapi.domain.model.FormaPagamento;
import br.com.juwer.algafoodapi.domain.repository.FormaPagamentoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/v1/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerSpringDoc {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @Autowired
    private FormaPagamentoDTODisassembler  formaPagamentoDTODisassembler;

    @Override
    @CheckSecurity.FormasPagamento.PodeConsultar
    @GetMapping
    public ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request){
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());
        String deepETag = "0";

        OffsetDateTime ultimaAtualizacao = formaPagamentoRepository.getDataUltimaAtualizacao();

        if(ultimaAtualizacao != null) {
            deepETag = String.valueOf(ultimaAtualizacao.toEpochSecond()); // converte em timestamp
        }

        if(request.checkNotModified(deepETag)) {
            return null; // retorna 304 not modified
        }

        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAll();
        CollectionModel<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoDTOAssembler
                .toCollectionModel(formaPagamentos);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(deepETag)
                .body(formaPagamentoDTOS);
    }

    @Override
    @CheckSecurity.FormasPagamento.PodeConsultar
    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDTO> buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = cadastroFormaPagamentoService
                .buscarOuFalhar(formaPagamentoId);

        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoDTOAssembler
                .toModel(formaPagamento);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(formaPagamentoDTO);
    }

    @Override
    @CheckSecurity.FormasPagamento.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoDTO adicionar(@RequestBody @Valid FormaPagamentoDTOInput formaPagamentoDTOInput) {
        FormaPagamento formaPagamento = formaPagamentoDTODisassembler
                .toDomainObject(formaPagamentoDTOInput);

        return formaPagamentoDTOAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamento));
    }

    @Override
    @CheckSecurity.FormasPagamento.PodeEditar
    @PutMapping("/{formaPagamentoID}")
    public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoID,
                                       @RequestBody @Valid FormaPagamentoDTOInput formaPagamentoDTOInput) {
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoID);
        formaPagamentoDTODisassembler.copyToDomainObject(formaPagamentoDTOInput, formaPagamento);
        return formaPagamentoDTOAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamento));
    }

    @Override
    @CheckSecurity.FormasPagamento.PodeEditar
    @DeleteMapping("/{formaPagamentoID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long formaPagamentoID){
        cadastroFormaPagamentoService.excluir(formaPagamentoID);
    }
}
