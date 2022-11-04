package br.com.juwer.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  @Override
  public Cidade salvar(Cidade cidade) {
    return entityManager.merge(cidade);
  }

  @Transactional
  @Override
  public void remover(Long cidadeId) {
    Cidade cidade = buscar(cidadeId);

    if(cidade == null){
      throw new EmptyResultDataAccessException(null, 1);
    }
    entityManager.remove(cidade);
  }
}
