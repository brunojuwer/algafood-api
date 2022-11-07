package br.com.juwer.algafoodapi.domain.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.juwer.algafoodapi.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository {

    List<FormaPagamento> listar();

    FormaPagamento buscar(Long id);

    FormaPagamento salvar(FormaPagamento formaPagamento);

    void remover(Long formaPagamentoId);
}
