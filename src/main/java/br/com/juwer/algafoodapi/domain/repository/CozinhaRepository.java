package br.com.juwer.algafoodapi.domain.repository;

import br.com.juwer.algafoodapi.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listar();

    Cozinha buscar(Long id);

    Cozinha salvar(Cozinha cozinha);

    void remover(Long cozinhaId);
}
