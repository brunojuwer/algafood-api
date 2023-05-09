package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.UsuarioDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.disassembler.UsuarioDTODisassembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.UsuarioDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos.UsuarioDTOInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos.UsuarioDTOInputPost;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos.UsuarioDTOInputSenha;
import br.com.juwer.algafoodapi.api.v1.openapi.controller.UsuarioControllerOpenApi;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import br.com.juwer.algafoodapi.domain.model.Usuario;
import br.com.juwer.algafoodapi.domain.repository.UsuarioRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;

    @Autowired
    private UsuarioDTODisassembler usuarioDTODisassembler;

    @Override
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioDTOAssembler.toCollectionModel(usuarios);
    }

    @Override
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping(path = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscaOuFalha(usuarioId);
        return usuarioDTOAssembler.toModel(usuario);
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO adicionar(@RequestBody @Valid UsuarioDTOInputPost usuarioDTOInputPost) {
        Usuario usuario = usuarioDTODisassembler.toDomainObject(usuarioDTOInputPost);
        Usuario usuarioSalvo = cadastroUsuarioService.salvar(usuario);

        return usuarioDTOAssembler.toModel(usuarioSalvo);
    }

    @Override
    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
    @PutMapping(path = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO atualizar(@PathVariable Long usuarioId,
                                @RequestBody @Valid UsuarioDTOInput usuarioDTOInput) {
        Usuario usuarioAtual = cadastroUsuarioService.buscaOuFalha(usuarioId);
        usuarioDTODisassembler.copyToDomainObject(usuarioDTOInput, usuarioAtual);
        return usuarioDTOAssembler.toModel(cadastroUsuarioService.salvar(usuarioAtual));
    }

    @Override
    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void senha(@PathVariable Long usuarioId, @RequestBody UsuarioDTOInputSenha usuarioDTOInputSenha) {
        String senhaAtual = usuarioDTOInputSenha.getSenhaAtual();
        String novaSenha = usuarioDTOInputSenha.getSenha();
        cadastroUsuarioService.alterarSenha(senhaAtual, novaSenha, usuarioId);
    }

    @Override
    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId) {
        cadastroUsuarioService.excluir(usuarioId);
    }
}
