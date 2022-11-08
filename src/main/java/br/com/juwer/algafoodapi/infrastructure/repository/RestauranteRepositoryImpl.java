package br.com.juwer.algafoodapi.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
  
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial,
    BigDecimal taxaFreteFinal) {

      var jpql = "from Restaurante where nome like :nome " 
                  + "and taxaFrete between :taxaFreteInicial and :taxaFreteFinal";
                  
      return entityManager.createQuery(jpql, Restaurante.class)
              .setParameter("nome", "%"+ nome +"%")
              .setParameter("taxaFreteInicial", taxaFreteInicial)
              .setParameter("taxaFreteFinal", taxaFreteFinal)
              .getResultList();
    }

}
