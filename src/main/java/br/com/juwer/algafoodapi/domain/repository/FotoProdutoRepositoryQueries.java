package br.com.juwer.algafoodapi.domain.repository;

import br.com.juwer.algafoodapi.domain.model.FotoProduto;

public interface FotoProdutoRepositoryQueries {

    FotoProduto salvar(FotoProduto foto);

    void delete(FotoProduto foto);
}
