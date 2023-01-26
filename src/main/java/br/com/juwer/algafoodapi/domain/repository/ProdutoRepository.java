package br.com.juwer.algafoodapi.domain.repository;

import br.com.juwer.algafoodapi.domain.model.Produto;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{

    Optional<Produto> findProdutoByRestauranteIdAndId(Long restauranteId, Long id);

    @Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
    List<Produto> findAtivosByRestauranteId(Restaurante restaurante);
}
