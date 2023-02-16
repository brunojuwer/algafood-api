package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.model.FotoProduto;
import br.com.juwer.algafoodapi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class CatalogoFotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream inputStream) {
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String nomeComUUID = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
        fotoExistente.ifPresent(fotoProduto -> produtoRepository.delete(fotoProduto));
        foto.setNomeArquivo(nomeComUUID);
        foto = produtoRepository.salvar(foto);
        produtoRepository.flush();

        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto
                .builder()
                .inputStream(inputStream)
                .nomeArquivo(foto.getNomeArquivo())
                .build();

        fotoStorageService.armazenar(novaFoto);

       return foto;
    }
}
