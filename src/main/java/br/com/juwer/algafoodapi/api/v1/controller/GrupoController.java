package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.GrupoDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.disassembler.GrupoDTODisassembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.GrupoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.GrupoDTOInput;
import br.com.juwer.algafoodapi.api.v1.openapi.controller.GrupoControllerOpenApi;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import br.com.juwer.algafoodapi.domain.repository.GrupoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @Autowired
    private GrupoDTODisassembler grupoDTODisassembler;

    @Override
    @GetMapping
    public CollectionModel<GrupoDTO> listar() {
        List<Grupo> grupos = grupoRepository.findAll();
        return grupoDTOAssembler.toCollectionModel(grupos);
    }

    @Override
    @GetMapping("/{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        return grupoDTOAssembler.toModel(cadastroGrupoService.buscaOuFalha(grupoId));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO adicionar(@RequestBody @Valid GrupoDTOInput grupoDTOInput) {
        Grupo grupo = grupoDTODisassembler.toDomainObject(grupoDTOInput);
        return grupoDTOAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

    @Override
    @PutMapping("/{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoDTOInput grupoDTOInput) {
        Grupo grupo = cadastroGrupoService.buscaOuFalha(grupoId);
        grupoDTODisassembler.copyToDomainObject(grupoDTOInput, grupo);

        return grupoDTOAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

    @Override
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long grupoId) {
        cadastroGrupoService.excluir(grupoId);
    }
}
