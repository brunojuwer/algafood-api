package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.GrupoDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.GrupoDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.GrupoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.GrupoDTOInput;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import br.com.juwer.algafoodapi.domain.repository.GrupoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @Autowired
    private GrupoDTODisassembler grupoDTODisassembler;

    @GetMapping
    public List<GrupoDTO> listar() {
        List<Grupo> grupos = grupoRepository.findAll();
        return grupoDTOAssembler.toCollectionModel(grupos);
    }

    @GetMapping("/{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        return grupoDTOAssembler.toModel(cadastroGrupoService.buscaOuFalha(grupoId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO adicionar(@RequestBody @Valid GrupoDTOInput grupoDTOInput) {
        Grupo grupo = grupoDTODisassembler.toDomainObject(grupoDTOInput);
        return grupoDTOAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

    @PutMapping("/{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoDTOInput grupoDTOInput) {
        Grupo grupo = cadastroGrupoService.buscaOuFalha(grupoId);
        grupoDTODisassembler.copyToDomainObject(grupoDTOInput, grupo);

        return grupoDTOAssembler.toModel(cadastroGrupoService.salvar(grupo));
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long grupoId) {
        cadastroGrupoService.excluir(grupoId);
    }
}
