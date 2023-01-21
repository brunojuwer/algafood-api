package br.com.juwer.algafoodapi.infrastructure.repository;

import br.com.juwer.algafoodapi.domain.model.Produto;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;

public class ProdutoRepositoryImpl extends CustomJpaRepositoryImpl<Produto, Long>{
    public ProdutoRepositoryImpl(JpaEntityInformation<Produto, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
