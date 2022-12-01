package br.com.juwer.algafoodapi.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.juwer.algafoodapi.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>{
    List<Cozinha> findAllByNomeContaining(String nome);
}
