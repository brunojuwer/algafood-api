package br.com.juwer.algafoodapi.infrastructure.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import br.com.juwer.algafoodapi.domain.model.Restaurante;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante>{
  
  private static final long serialVersionUID = 1L;
  
  private String nome;

  @Override
  @Nullable
  public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query,
      CriteriaBuilder builder) {
    
    return builder.like(root.get("nome"), "%" + nome + "%");
  }
}
