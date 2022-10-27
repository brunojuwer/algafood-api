package br.com.juwer.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

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

  @Override
  public Permissao salvar(Permissao permissao) {
    return entityManager.merge(permissao);
  }

  @Override
  public void remover(Permissao permissao) {
    permissao = buscar(permissao.getId());
    entityManager.remove(permissao);
  }
  
}
