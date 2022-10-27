package br.com.juwer.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

  @PersistenceContext
  private EntityManager entityManager;


  @Override
  public List<Cidade> listar() {
      return entityManager
            .createQuery("from Cidade", Cidade.class)
            .getResultList();
  }

  @Override
  public Cidade buscar(Long id) {
    return entityManager.find(Cidade.class, id);
  }

  @Override
  public Cidade salvar(Cidade cidade) {
    return entityManager.merge(cidade);
  }

  @Override
  public void remover(Cidade cidade) {
    cidade = buscar(cidade.getId());
    entityManager.remove(cidade);
  }
}
