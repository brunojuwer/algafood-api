package br.com.juwer.algafoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.juwer.algafoodapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

  @Query("from Restaurante r left join fetch r.cozinha left join fetch r.formasPagamento")
  List<Restaurante> findAll();

  List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
  
  List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);
}
