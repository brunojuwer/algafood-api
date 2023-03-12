package br.com.juwer.algafoodapi.api.v2.controller;

import br.com.juwer.algafoodapi.api.utils.ResourceUriHelper;
import br.com.juwer.algafoodapi.api.v2.assembler.CidadeDTOAssemblerV2;
import br.com.juwer.algafoodapi.api.v2.disassembler.CidadeDTODisassemblerV2;
import br.com.juwer.algafoodapi.api.v2.model.dto.CidadeDTOV2;
import br.com.juwer.algafoodapi.api.v2.model.dtoinput.CidadeDTOInputV2;
import br.com.juwer.algafoodapi.core.web.AlgaMediaTypes;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.repository.CidadeRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/cidades", produces = AlgaMediaTypes.V2_APPLICATION_JSON_VALUE)
public class CidadeControllerV2  {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeDTOAssemblerV2 cidadeDTOAssembler;

    @Autowired
    private CidadeDTODisassemblerV2 cidadeDTODisassembler;

    @GetMapping
    public CollectionModel<CidadeDTOV2> listar() {
        return cidadeDTOAssembler.toCollectionModel(cidadeRepository.findAllCidades());
    }

    @GetMapping(value = "/{cidadeId}")
    public CidadeDTOV2 buscar(@PathVariable Long cidadeId) {
        return cidadeDTOAssembler.toModel(cadastroCidadeService.buscaOuFalha(cidadeId));
    }

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

//    @DeleteMapping("/{cidadeId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void remover(@PathVariable Long cidadeId) {
//        cadastroCidadeService.excluir(cidadeId);
//    }
}
