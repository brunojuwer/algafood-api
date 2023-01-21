package br.com.juwer.algafoodapi.domain.repository;

import br.com.juwer.algafoodapi.domain.model.Produto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{

    Produto findProdutoByRestauranteIdAndId(Long restauranteId, Long id);
}
