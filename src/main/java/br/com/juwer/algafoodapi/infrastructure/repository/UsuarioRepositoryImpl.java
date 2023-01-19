package br.com.juwer.algafoodapi.infrastructure.repository;

import br.com.juwer.algafoodapi.domain.model.Usuario;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;

public class UsuarioRepositoryImpl extends CustomJpaRepositoryImpl<Usuario, Long> {
    public UsuarioRepositoryImpl(JpaEntityInformation<Usuario, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }
}
