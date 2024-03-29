package br.com.juwer.algafoodapi.api.v1.controller;


import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.api.v1.assembler.GrupoDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.GrupoDTO;
import br.com.juwer.algafoodapi.api.v1.springdoc.controller.UsuarioGruposControllerSpringDoc;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import br.com.juwer.algafoodapi.domain.repository.GrupoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios/{usuarioId}/grupos")
public class UsuarioGruposController implements UsuarioGruposControllerSpringDoc {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    @Override
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<GrupoDTO> listar(@PathVariable Long usuarioId) {
        List<Grupo> grupos = grupoRepository.findGruposByUsusarioId(usuarioId);

        CollectionModel<GrupoDTO> gruposDTO = grupoDTOAssembler.toCollectionModel(grupos).removeLinks()
                .add(hateoasAlgaLinks.linkToClienteGrupos(usuarioId).withSelfRel())
                .add(hateoasAlgaLinks.linkToUsuarioGruposAssociar(usuarioId, "associar"));

        gruposDTO.getContent().forEach(grupo -> {
            grupo.add(hateoasAlgaLinks.linkToUsuarioGruposDesassociar(usuarioId, grupo.getId(), "desassociar"));
        });

        return gruposDTO;
    }

    @Override
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PutMapping("/{grupoId}")
    public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{grupoId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
        return ResponseEntity.noContent().build();
    }
}
