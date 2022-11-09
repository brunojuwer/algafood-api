package br.com.juwer.algafoodapi.infrastructure.specs;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import br.com.juwer.algafoodapi.domain.model.Restaurante;

public class RestauranteComFreteGratisSpec implements Specification<Restaurante> {

  private static final long serialVersionUID = 1L;

  @Override
  @Nullable
  public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, 
    CriteriaBuilder builder) {

    return builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
  }
}
