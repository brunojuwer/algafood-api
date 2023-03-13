package br.com.juwer.algafoodapi.api.v2.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.CozinhaDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.disassembler.CozinhaDTODisassembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.CozinhaDTOInput;
import br.com.juwer.algafoodapi.api.v1.openapi.controller.CozinhaControllerOpenApi;
import br.com.juwer.algafoodapi.api.v2.assembler.CozinhaDTOAssemblerV2;
import br.com.juwer.algafoodapi.api.v2.disassembler.CozinhaDTODisassemblerV2;
import br.com.juwer.algafoodapi.api.v2.model.dto.CozinhaDTOV2;
import br.com.juwer.algafoodapi.api.v2.model.dtoinput.CozinhaDTOInputV2;
import br.com.juwer.algafoodapi.api.v2.openapi.controller.CozinhaControllerV2OpenApi;
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

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v2/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaControllerV2 implements CozinhaControllerV2OpenApi {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cozinhaService;

    @Autowired
    private CozinhaDTOAssemblerV2 cozinhaDTOAssembler;

    @Autowired
    private CozinhaDTODisassemblerV2 cozinhaDTODisassembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @Override
    @GetMapping
    public PagedModel<CozinhaDTOV2> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhas = cozinhaRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(cozinhas, cozinhaDTOAssembler);
    }

    @Override
    @GetMapping("/{cozinhaId}")
    public CozinhaDTOV2 buscar(@PathVariable Long cozinhaId) {
        return cozinhaDTOAssembler.toModel(cozinhaService.buscaOuFalha(cozinhaId));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaDTOV2 adicionar(@RequestBody @Valid CozinhaDTOInputV2 cozinhaIdDTOInput) {
        Cozinha cozinha = cozinhaDTODisassembler.toDomainObject(cozinhaIdDTOInput);
        return cozinhaDTOAssembler.toModel(cozinhaService.salvar(cozinha));
    }

    @Override
    @PutMapping("/{cozinhaId}")
    public CozinhaDTOV2 atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaDTOInputV2 cozinha) {
        Cozinha cozinhaAtual = cozinhaService.buscaOuFalha(cozinhaId);
        cozinhaDTODisassembler.copyToDomainObject(cozinha, cozinhaAtual);

        return cozinhaDTOAssembler.toModel(cozinhaService.salvar(cozinhaAtual));
    }

    @Override
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(Long cozinhaId) {
        cozinhaService.excluir(cozinhaId);
    }
}
