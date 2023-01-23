package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.UsuarioDTOAssembler;
import br.com.juwer.algafoodapi.api.model.dto.UsuarioDTO;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/usuarios")
public class RestauranteUsuarioController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;

    @GetMapping
    public List<UsuarioDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscaOuFalha(restauranteId);

        return usuarioDTOAssembler.toCollectionModel(restaurante.getUsuarios());
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.associarUsuario(restauranteId, usuarioId);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desaassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        cadastroRestauranteService.desassociarUsuario(restauranteId, usuarioId);
    }
}
