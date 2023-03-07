package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.CozinhaDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.CozinhaDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.CozinhaDTOInput;
import br.com.juwer.algafoodapi.api.openapi.controller.CozinhaControllerOpenApi;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.repository.CozinhaRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

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
    @GetMapping
    public PagedModel<CozinhaDTO> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        // retorna PagedModel<CozinhaDTO>
        return pagedResourcesAssembler.toModel(cozinhasPage, cozinhaDTOAssembler);
    }

    @Override
    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId){
        return cozinhaDTOAssembler.toModel(cozinhaService.buscaOuFalha(cozinhaId));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTO adicionar(@RequestBody @Valid CozinhaDTOInput cozinhaIdDTOInput){
        Cozinha cozinha = cozinhaDTODisassembler.toDomainObject(cozinhaIdDTOInput);
        return cozinhaDTOAssembler.toModel(cozinhaService.salvar(cozinha));
    }

    @Override
    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@PathVariable Long cozinhaId,
                                @RequestBody @Valid CozinhaDTOInput cozinha) {

        Cozinha cozinhaAtual = cozinhaService.buscaOuFalha(cozinhaId);
        cozinhaDTODisassembler.copyToDomainObject(cozinha, cozinhaAtual);

        return cozinhaDTOAssembler.toModel(cozinhaService.salvar(cozinhaAtual));
    }

    @Override
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }

}    
