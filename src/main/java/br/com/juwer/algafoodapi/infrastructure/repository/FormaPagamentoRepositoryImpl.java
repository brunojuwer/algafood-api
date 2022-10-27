package br.com.juwer.algafoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

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

  @Override
  public FormaPagamento salvar(FormaPagamento formaPagamento) {
    return entityManager.merge(formaPagamento);
  }

  @Override
  public void remover(FormaPagamento formaPagamento) {
    formaPagamento = buscar(formaPagamento.getId());
    entityManager.remove(formaPagamento);
  }
  
}
