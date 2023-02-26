package br.com.juwer.algafoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import br.com.juwer.algafoodapi.domain.projections.RestauranteResumo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.juwer.algafoodapi.domain.model.Restaurante;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

  @Query("from Restaurante r join fetch r.cozinha")
  List<Restaurante> findAll();

  @Query(value = "select new br.com.juwer.algafoodapi.domain.projections.RestauranteResumo(r.id, r.nome, r.taxaFrete, c.id, c.nome) " +
          "from Restaurante r " +
          "join r.cozinha c on r.cozinha.id = c.id")
  List<RestauranteResumo> findAllRestauranteResumo();

  List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
  
  List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);
}
