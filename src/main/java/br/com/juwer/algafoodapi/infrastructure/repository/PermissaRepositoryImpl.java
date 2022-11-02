package br.com.juwer.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.juwer.algafoodapi.domain.model.Permissao;
import br.com.juwer.algafoodapi.domain.repository.PermissaoRepository;

@Component
public class PermissaRepositoryImpl implements PermissaoRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Permissao> listar() {
    return entityManager
           .createQuery("from Permissao", Permissao.class)
           .getResultList();
  }

  @Override
  public Permissao buscar(Long id) {
    return entityManager.find(Permissao.class, id);
  }

  @Transactional
  @Override
  public Permissao salvar(Permissao permissao) {
    return entityManager.merge(permissao);
  }

  @Transactional
  @Override
  public void remover(Long permissaoId) {
    Permissao permissao = buscar(permissaoId);

    if (permissao == null) {
      throw new EmptyResultDataAccessException(null, 1);
    }
    
    entityManager.remove(permissao);
  }
  
}
