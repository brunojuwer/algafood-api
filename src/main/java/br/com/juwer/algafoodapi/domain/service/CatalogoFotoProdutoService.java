package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.exception.FotoNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.model.FotoProduto;
import br.com.juwer.algafoodapi.domain.repository.ProdutoRepository;
import br.com.juwer.algafoodapi.infrastructure.storage.StorageException;
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
        String nomeArquivoExistente = null;

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

        if (fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeComUUID);
        foto = produtoRepository.salvar(foto);
        // descarrega no banco e se der algum problema nem salva a imagem abaixo
        produtoRepository.flush();

        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto
                .builder()
                .inputStream(inputStream)
                .nomeArquivo(foto.getNomeArquivo())
                .build();

        fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

       return foto;
    }

    public FotoProduto buscaOuFalha(Long restauranteId, Long produtoId) {
        return produtoRepository.findFotoById(restauranteId, produtoId)
               .orElseThrow(() -> new FotoNaoEncontradaException("Não foi encontrada nenhuma foto"));
    }
}
