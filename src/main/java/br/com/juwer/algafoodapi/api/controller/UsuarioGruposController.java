package br.com.juwer.algafoodapi.api.controller;


import br.com.juwer.algafoodapi.api.assembler.GrupoDTOAssembler;
import br.com.juwer.algafoodapi.api.model.dto.GrupoDTO;
import br.com.juwer.algafoodapi.api.openapi.controller.UsuarioGruposControllerOpenApi;
import br.com.juwer.algafoodapi.api.utils.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import br.com.juwer.algafoodapi.domain.model.Usuario;
import br.com.juwer.algafoodapi.domain.repository.GrupoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGruposController implements UsuarioGruposControllerOpenApi {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoDTOAssembler grupoDTOAssembler;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<GrupoDTO> listar(@PathVariable Long usuarioId) {
        List<Grupo> grupos = grupoRepository.findGruposByUsusarioId(usuarioId);

        return grupoDTOAssembler.toCollectionModel(grupos).removeLinks()
                .add(hateoasAlgaLinks.linkToClienteGrupos(usuarioId).withSelfRel());
    }

    @Override
    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
    }

    @Override
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
    }
}
