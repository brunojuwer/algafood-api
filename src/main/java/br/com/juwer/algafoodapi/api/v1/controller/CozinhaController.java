package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.CozinhaDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.disassembler.CozinhaDTODisassembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.CozinhaDTOInput;
import br.com.juwer.algafoodapi.api.v1.springdoc.controller.CozinhaControllerSpringDoc;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.repository.CozinhaRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerSpringDoc {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    @Autowired
    private CozinhaDTOAssembler cozinhaDTOAssembler;

    @Autowired
    private CozinhaDTODisassembler cozinhaDTODisassembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @Override
    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping
    public PagedModel<CozinhaDTO> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(cozinhasPage, cozinhaDTOAssembler);
    }

    @Override
    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId){
        return cozinhaDTOAssembler.toModel(cozinhaService.buscaOuFalha(cozinhaId));
    }

    @Override
    @CheckSecurity.Cozinhas.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO adicionar(@RequestBody @Valid CozinhaDTOInput cozinhaIdDTOInput){
        Cozinha cozinha = cozinhaDTODisassembler.toDomainObject(cozinhaIdDTOInput);
        return cozinhaDTOAssembler.toModel(cozinhaService.salvar(cozinha));
    }

    @Override
    @CheckSecurity.Cozinhas.PodeEditar
    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId,
                                @RequestBody @Valid CozinhaDTOInput cozinha) {

        Cozinha cozinhaAtual = cozinhaService.buscaOuFalha(cozinhaId);
        cozinhaDTODisassembler.copyToDomainObject(cozinha, cozinhaAtual);

        return cozinhaDTOAssembler.toModel(cozinhaService.salvar(cozinhaAtual));
    }

    @Override
    @CheckSecurity.Cozinhas.PodeEditar
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }

}    
