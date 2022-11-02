package br.com.juwer.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.juwer.algafoodapi.domain.model.FormaPagamento;
import br.com.juwer.algafoodapi.domain.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository{

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<FormaPagamento> listar() {
    return entityManager
           .createQuery("from FormaPagamento", FormaPagamento.class)
           .getResultList();
  }

  @Override
  public FormaPagamento buscar(Long id) {
    return entityManager.find(FormaPagamento.class, id);
  }

  @Transactional
  @Override
  public FormaPagamento salvar(FormaPagamento formaPagamento) {
    return entityManager.merge(formaPagamento);
  }

  @Transactional
  @Override
  public void remover(Long formaPagamentoId) {
    FormaPagamento formaPagamento = buscar(formaPagamentoId);

    if(formaPagamento == null){
      throw new EmptyResultDataAccessException(null, 1);
    }

    entityManager.remove(formaPagamento);
  }
  
}
