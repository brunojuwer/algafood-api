package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.GrupoNaoEncontradoException;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import br.com.juwer.algafoodapi.domain.model.Permissao;
import br.com.juwer.algafoodapi.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CadastroGrupoService {

    private final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso.";

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissaoService;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long grupoId) {
        try {
            grupoRepository.deleteById(grupoId);
            grupoRepository.flush();
        } catch(EmptyResultDataAccessException e){
            throw new GrupoNaoEncontradoException(grupoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_GRUPO_EM_USO, grupoId)
            );
        }
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        Set<Permissao> permissoes = buscaOuFalha(grupoId).getPermissoes();
        Permissao permissao = cadastroPermissaoService.buscaOuFalha(permissaoId);
        permissoes.add(permissao);
    }

    @Transactional
    public void desaAssociarPermissao(Long grupoId, Long permissaoId) {
        Set<Permissao> permissoes = buscaOuFalha(grupoId).getPermissoes();
        Permissao permissao = cadastroPermissaoService.buscaOuFalha(permissaoId);
        permissoes.remove(permissao);
    }

    public Grupo buscaOuFalha(Long grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
    }
}
