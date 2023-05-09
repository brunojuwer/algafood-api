package br.com.juwer.algafoodapi.api.v2.controller;

import br.com.juwer.algafoodapi.api.utils.ResourceUriHelper;
import br.com.juwer.algafoodapi.api.v2.assembler.CidadeDTOAssemblerV2;
import br.com.juwer.algafoodapi.api.v2.disassembler.CidadeDTODisassemblerV2;
import br.com.juwer.algafoodapi.api.v2.model.dto.CidadeDTOV2;
import br.com.juwer.algafoodapi.api.v2.model.dtoinput.CidadeDTOInputV2;
import br.com.juwer.algafoodapi.api.v2.openapi.controller.CidadeControllerV2OpenApi;
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
@RequestMapping(value = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 implements CidadeControllerV2OpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeDTOAssemblerV2 cidadeDTOAssembler;

    @Autowired
    private CidadeDTODisassemblerV2 cidadeDTODisassembler;

    @Override
    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping
    public CollectionModel<CidadeDTOV2> listar() {
        return cidadeDTOAssembler.toCollectionModel(cidadeRepository.findAllCidades());
    }

    @Override
    @CheckSecurity.Cidades.PodeConsultar
    @GetMapping(value = "/{cidadeId}")
    public CidadeDTOV2 buscar(@PathVariable Long cidadeId) {
        return cidadeDTOAssembler.toModel(cadastroCidadeService.buscaOuFalha(cidadeId));
    }

    @Override
    @CheckSecurity.Cidades.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTOV2 adicionar(@RequestBody @Valid CidadeDTOInputV2 cidadeDTOInput) {
        try {
            Cidade cidade = cidadeDTODisassembler.toDomainObject(cidadeDTOInput);
            CidadeDTOV2 cidadeDTO = cidadeDTOAssembler.toModel(cadastroCidadeService.salvar(cidade));

            ResourceUriHelper.addUriResponseHeader(cidadeDTO.getIdCidade());

            return cidadeDTO;
        } catch(EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    @CheckSecurity.Cidades.PodeEditar
    @PutMapping("/{cidadeId}")
    public CidadeDTOV2 atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeDTOInputV2 cidadeDTOInput) {
        Cidade cidadeAtual = cadastroCidadeService.buscaOuFalha(cidadeId);
        cidadeDTODisassembler.copyToDomainObject(cidadeDTOInput, cidadeAtual);

        try {
            return cidadeDTOAssembler.toModel(cadastroCidadeService.salvar(cidadeAtual));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @CheckSecurity.Cidades.PodeEditar
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }
}
