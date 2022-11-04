package br.com.juwer.algafoodapi.domain.repository;

import java.util.List;

import br.com.juwer.algafoodapi.domain.model.Restaurante;

public interface RestauranteRepository {

    List<Restaurante> listar();

    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante restaurante);

    void remover(Long restauranteId);

}
