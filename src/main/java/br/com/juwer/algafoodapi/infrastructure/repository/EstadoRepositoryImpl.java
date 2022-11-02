package br.com.juwer.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.com.juwer.algafoodapi.domain.model.Estado;
import br.com.juwer.algafoodapi.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {


  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Estado> listar() {
    return entityManager
          .createQuery("from Estado", Estado.class)
          .getResultList();
  }

  @Override
  public Estado buscar(Long id) {
    return entityManager.find(Estado.class, id);
  }

  @Transactional
  @Override
  public Estado salvar(Estado estado) {
    return entityManager.merge(estado);
  }

  @Transactional
  @Override
  public void remover(Long estadoId) {
    Estado estado = buscar(estadoId);
    if (estado == null) {
      throw new EmptyResultDataAccessException(null, 1);
  }
    entityManager.remove(estado);
  }
  
}
