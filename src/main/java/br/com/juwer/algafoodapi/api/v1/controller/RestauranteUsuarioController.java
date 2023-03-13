package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.UsuarioDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.UsuarioDTO;
import br.com.juwer.algafoodapi.api.v1.openapi.controller.RestauranteUsuarioControllerOpenApi;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/usuarios")
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<UsuarioDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscaOuFalha(restauranteId);

        CollectionModel<UsuarioDTO> usuariosDTO = usuarioDTOAssembler.toCollectionModel(restaurante.getUsuarios())
                .removeLinks()
                .add(hateoasAlgaLinks.linkToRestauranteUsuarios(restauranteId))
                .add(hateoasAlgaLinks.linkToRestauranteUsuariosAssociar(restauranteId, "associar"));

        usuariosDTO.getContent().forEach(usuario -> {
            usuario.add(hateoasAlgaLinks.linkToRestauranteUsuariosDesassociar(restauranteId, usuario.getId(), "desassociar"));
        });

        return usuariosDTO;
    }

    @Override
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.associarUsuario(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.desassociarUsuario(restauranteId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
