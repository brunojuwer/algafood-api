package br.com.juwer.algafoodapi.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
  
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial,
    BigDecimal taxaFreteFinal) {
    
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();

      CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
      Root<Restaurante> root = criteria.from(Restaurante.class);
      
      List<Predicate> predicados = new ArrayList<>();

      if(StringUtils.hasText(nome)){
        predicados.add(builder
          .like(root.get("nome"), "%" + nome + "%"));
      }

      if(taxaFreteInicial != null) {
        predicados.add(builder
          .greaterThanOrEqualTo(root.get("taxaFrete") , taxaFreteInicial));
      }

      if(taxaFreteFinal != null) {
        predicados.add(builder
          .lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
      }

      criteria.where(predicados.toArray(new Predicate[0]));

      return entityManager.createQuery(criteria).getResultList();
    }
}
