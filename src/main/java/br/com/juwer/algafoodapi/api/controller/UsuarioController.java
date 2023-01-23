package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.UsuarioDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.UsuarioDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.UsuarioDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.usuariodtos.UsuarioDTOInput;
import br.com.juwer.algafoodapi.api.model.dto.input.usuariodtos.UsuarioDTOInputPost;
import br.com.juwer.algafoodapi.api.model.dto.input.usuariodtos.UsuarioDTOInputSenha;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.model.Usuario;
import br.com.juwer.algafoodapi.domain.repository.UsuarioRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioDTOAssembler usuarioDTOAssembler;

    @Autowired
    private UsuarioDTODisassembler usuarioDTODisassembler;

    @GetMapping
    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioDTOAssembler.toCollectionModel(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscaOuFalha(usuarioId);
        return usuarioDTOAssembler.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO adicionar(@RequestBody @Valid UsuarioDTOInputPost usuarioDTOInputPost) {
        Usuario usuario = usuarioDTODisassembler.toDomainObject(usuarioDTOInputPost);
        Usuario usuarioSalvo = cadastroUsuarioService.salvar(usuario);

        return usuarioDTOAssembler.toModel(usuarioSalvo);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@PathVariable Long usuarioId,
                                @RequestBody @Valid UsuarioDTOInput usuarioDTOInput) {
        Usuario usuarioAtual = cadastroUsuarioService.buscaOuFalha(usuarioId);
        usuarioDTODisassembler.copyToDomainObject(usuarioDTOInput, usuarioAtual);
        return usuarioDTOAssembler.toModel(cadastroUsuarioService.salvar(usuarioAtual));
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void senha(@PathVariable Long usuarioId, @RequestBody UsuarioDTOInputSenha usuarioDTOInputSenha) {
        String senhaAtual = usuarioDTOInputSenha.getSenhaAtual();
        String novaSenha = usuarioDTOInputSenha.getSenha();
        cadastroUsuarioService.alterarSenha(senhaAtual, novaSenha, usuarioId);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId) {
        cadastroUsuarioService.excluir(usuarioId);
    }

}