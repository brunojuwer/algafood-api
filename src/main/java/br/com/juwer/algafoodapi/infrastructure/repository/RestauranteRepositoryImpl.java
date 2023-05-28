package br.com.juwer.algafoodapi.infrastructure.repository;

import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.juwer.algafoodapi.infrastructure.service.specs.RestauranteSpecs.comFreteGratis;
import static br.com.juwer.algafoodapi.infrastructure.service.specs.RestauranteSpecs.comNomeSemelhante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {
  
  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  @Lazy
  private RestauranteRepository restauranteRepository;

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

    @Override
  public List<Restaurante> findComFreteGratis(String nome) {
    return restauranteRepository
      .findAll(comFreteGratis().and(comNomeSemelhante(nome)));  
  }

    @Override
    public List<Restaurante> findAllResumo() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteriaQuery = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

        criteriaQuery.multiselect(root.get("id"), root.get("nome"), root.get("taxaFrete"), root.get("cozinha"))
                .from(Restaurante.class).join("cozinha");

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
