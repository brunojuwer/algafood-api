package br.com.juwer.algafoodapi.domain.repository;

import br.com.juwer.algafoodapi.domain.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{

    Optional<Produto> findProdutoByRestauranteIdAndId(Long restauranteId, Long id);
}
