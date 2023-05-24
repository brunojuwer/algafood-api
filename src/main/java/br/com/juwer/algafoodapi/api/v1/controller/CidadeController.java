package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.utils.ResourceUriHelper;
import br.com.juwer.algafoodapi.api.v1.assembler.CidadeDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.disassembler.CidadeDTODisassembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.CidadeDTOInput;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.repository.CidadeRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeDTOAssembler cidadeDTOAssembler;

    @Autowired
    private CidadeDTODisassembler cidadeDTODisassembler;

    @Deprecated
    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping
    public CollectionModel<CidadeDTO> listar() {
        return cidadeDTOAssembler.toCollectionModel(cidadeRepository.findAllCidades());
    }

    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping(value = "/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        return cidadeDTOAssembler.toModel(cadastroCidadeService.buscaOuFalha(cidadeId));
    }

    @CheckSecurity.Cidades.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO adicionar(@RequestBody @Valid CidadeDTOInput cidadeDTOInput) {
        try {
            Cidade cidade = cidadeDTODisassembler.toDomainObject(cidadeDTOInput);
            CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cadastroCidadeService.salvar(cidade));

            ResourceUriHelper.addUriResponseHeader(cidadeDTO.getId());

            return cidadeDTO;
        } catch(EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @CheckSecurity.Cidades.PodeEditar
    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeDTOInput cidadeDTOInput) {
        Cidade cidadeAtual = cadastroCidadeService.buscaOuFalha(cidadeId);
        cidadeDTODisassembler.copyToDomainObject(cidadeDTOInput, cidadeAtual);

        try {
            return cidadeDTOAssembler.toModel(cadastroCidadeService.salvar(cidadeAtual));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @CheckSecurity.Cidades.PodeEditar
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }
}
