package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.exception.UsuarioNaoEncontradoException;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import br.com.juwer.algafoodapi.domain.model.Usuario;
import br.com.juwer.algafoodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroUsuarioService {

    private static final String MSG_USUARIO_EM_USO = "Usuário de código %d não pode ser removido, pois está em uso";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuarioRepository.detach(usuario);
        Optional<Usuario> usuarioEmail = usuarioRepository.findByEmail(usuario.getEmail());

        if(usuarioEmail.isPresent() && !usuarioEmail.get().equals(usuario)) {
            throw new NegocioException(String.format(
                    "Já existe um cadastro de usuário com e-mail: %s", usuario.getEmail()
            ));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void excluir(Long usuarioId) {
        try {
            usuarioRepository.deleteById(usuarioId);
            usuarioRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new UsuarioNaoEncontradoException(usuarioId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, usuarioId));
        }
    }

    @Transactional
    public void alterarSenha(String senhaAtual, String novaSenha, Long usuarioId) {
        Usuario usuario = this.buscaOuFalha(usuarioId);

        if(!usuario.senhaCoincide(senhaAtual)) {
            throw new NegocioException("A senha atual não conincide com a informada");
        }
        usuario.setSenha(novaSenha);
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscaOuFalha(usuarioId);
        Grupo grupo = cadastroGrupoService.buscaOuFalha(grupoId);
        usuario.getGrupos().add(grupo);
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscaOuFalha(usuarioId);
        Grupo grupo = cadastroGrupoService.buscaOuFalha(grupoId);
        usuario.getGrupos().remove(grupo);
    }

    public Usuario buscaOuFalha(Long usuarioId){
        return usuarioRepository.findById(usuarioId).orElseThrow(
                () -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
