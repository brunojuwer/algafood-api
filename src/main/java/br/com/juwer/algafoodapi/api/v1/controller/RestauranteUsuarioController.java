package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.api.v1.assembler.UsuarioDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.UsuarioDTO;
import br.com.juwer.algafoodapi.api.v1.springdoc.controller.RestauranteUsuarioControllerSpringDoc;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import br.com.juwer.algafoodapi.core.security.SecurityUtils;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/usuarios")
public class RestauranteUsuarioController implements RestauranteUsuarioControllerSpringDoc {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<UsuarioDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscaOuFalha(restauranteId);
        CollectionModel<UsuarioDTO> usuariosDTO;

        if (securityUtils.hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES")) {
            usuariosDTO = usuarioDTOAssembler.toCollectionModel(restaurante.getResponsaveis())
                    .removeLinks()
                    .add(hateoasAlgaLinks.linkToRestauranteUsuarios(restauranteId))
                    .add(hateoasAlgaLinks.linkToRestauranteUsuariosAssociar(restauranteId, "associar"));

            usuariosDTO.getContent().forEach(usuario -> {
                usuario.add(hateoasAlgaLinks.linkToRestauranteUsuariosDesassociar(restauranteId, usuario.getId(), "desassociar"));
            });
            return usuariosDTO;
        } else {
            return usuarioDTOAssembler.toCollectionModel(restaurante.getResponsaveis());
        }
    }

    @Override
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.associarUsuario(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.desassociarUsuario(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
