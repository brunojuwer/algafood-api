package br.com.juwer.algafoodapi.infrastructure.repository;

import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.repository.CozinhaRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar(){
        return entityManager
                .createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }
    @Override
    public Cozinha buscar(Long id){
        return entityManager.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha){
        return entityManager.merge(cozinha);
    }

    @Override
    @Transactional
    public void remover(Long cozinhaId){
       Cozinha cozinha = buscar(cozinhaId);

       if (cozinha == null) {
            throw new EmptyResultDataAccessException(null, 1);
        }

        entityManager.remove(cozinha);

    }
}
