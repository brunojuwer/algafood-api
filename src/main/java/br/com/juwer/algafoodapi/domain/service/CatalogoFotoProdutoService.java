package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.model.FotoProduto;
import br.com.juwer.algafoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
        fotoExistente.ifPresent(fotoProduto -> produtoRepository.delete(fotoProduto));

       return produtoRepository.salvar(foto);
    }
}
