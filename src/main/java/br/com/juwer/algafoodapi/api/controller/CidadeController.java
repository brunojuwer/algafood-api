package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.CidadeDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.CidadeDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.CidadeDTOInput;
import br.com.juwer.algafoodapi.api.openapi.controller.CidadeControllerOpenApi;
import br.com.juwer.algafoodapi.api.utils.ResourceUriHelper;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.repository.CidadeRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping(value = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeDTOAssembler cidadeDTOAssembler;

    @Autowired
    private CidadeDTODisassembler cidadeDTODisassembler;

    @GetMapping
    public CollectionModel<CidadeDTO> listar() {
        List<CidadeDTO> cidadesDTO = cidadeDTOAssembler.toCollectionModel(cidadeRepository.findAllCidades());

        cidadesDTO.forEach(cidadeDTO -> {
            cidadeDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class)
                            .buscar(cidadeDTO.getId()))
                    .withSelfRel());

            cidadeDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).listar())
                    .withRel("cidades"));

            cidadeDTO.getEstado().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class)
                            .buscar(cidadeDTO.getEstado().getId()))
                    .withSelfRel());
        });

        CollectionModel<CidadeDTO> cidadeDTOSColletionModel = CollectionModel.of(cidadesDTO);

        cidadeDTOSColletionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class)
                .listar()).withSelfRel());

        return cidadeDTOSColletionModel;
    }

    @GetMapping(value = "/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        CidadeDTO cidadeDTO = cidadeDTOAssembler.toModel(cadastroCidadeService.buscaOuFalha(cidadeId));

        cidadeDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class)
                        .buscar(cidadeDTO.getId()))
                .withSelfRel());

        cidadeDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CidadeController.class).listar())
                .withRel("cidades"));

        cidadeDTO.getEstado().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EstadoController.class)
                        .buscar(cidadeDTO.getEstado().getId()))
                .withSelfRel());

        return cidadeDTO;
    }

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

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }
}
