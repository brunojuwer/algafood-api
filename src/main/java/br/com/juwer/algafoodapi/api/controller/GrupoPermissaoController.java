package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.PermissoesDTOAssembler;
import br.com.juwer.algafoodapi.api.model.dto.PermissaoDTO;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import br.com.juwer.algafoodapi.domain.repository.PermissaoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private PermissoesDTOAssembler permissoesDTOAssembler;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @GetMapping
    public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscaOuFalha(grupoId);

        return permissoesDTOAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.associarPermissao(grupoId, permissaoId);
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.desaAssociarPermissao(grupoId, permissaoId);
    }
}
