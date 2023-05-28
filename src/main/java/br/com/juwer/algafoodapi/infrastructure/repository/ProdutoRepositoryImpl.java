package br.com.juwer.algafoodapi.infrastructure.repository;

import br.com.juwer.algafoodapi.domain.model.FotoProduto;
import br.com.juwer.algafoodapi.domain.repository.FotoProdutoRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProdutoRepositoryImpl implements FotoProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public FotoProduto salvar(FotoProduto foto) {
        return manager.merge(foto);
    }

    @Override
    @Transactional
    public void delete(FotoProduto foto) {
        manager.remove(foto);
    }
}
