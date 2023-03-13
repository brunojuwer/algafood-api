package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.PermissoesDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.PermissaoDTO;
import br.com.juwer.algafoodapi.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import br.com.juwer.algafoodapi.domain.repository.PermissaoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private PermissoesDTOAssembler permissoesDTOAssembler;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<PermissaoDTO> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscaOuFalha(grupoId);

        CollectionModel<PermissaoDTO> permissoesDTO = permissoesDTOAssembler
                .toCollectionModel(grupo.getPermissoes())
                .removeLinks()
                .add(hateoasAlgaLinks.linkToPermissoes(grupoId, IanaLinkRelations.SELF_VALUE))
                .add(hateoasAlgaLinks.linkToGrupoPermissoesAssociar(grupoId, "associar"));

        permissoesDTO.getContent().forEach(permissao -> {
            permissao.add(hateoasAlgaLinks
                    .linkToGrupoPermissoesDesassociar(grupoId, permissao.getId(), "desassociar"));
        });

        return permissoesDTO;
    }

    @Override
    @PutMapping("/{permissaoId}")
    public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.associarPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{permissaoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupoService.desaAssociarPermissao(grupoId, permissaoId);
        return ResponseEntity.noContent().build();
    }
}
