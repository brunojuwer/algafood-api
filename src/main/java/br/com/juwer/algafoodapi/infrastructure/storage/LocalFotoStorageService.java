package br.com.juwer.algafoodapi.infrastructure.storage;

import br.com.juwer.algafoodapi.domain.service.FotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalFotoStorageService implements FotoStorageService {

    @Value("${algafood.storage.local.diretorio-fotos}")
    private Path diretorioFotos;

    @Override
    public InputStream recuperar(String nomeArquivo) {
        Path arquivoPath = getArquivoPath(nomeArquivo);
        try {
            return Files.newInputStream(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar o arquivo", e);
        }
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new StorageException("Não foi possivel armazenar o arquivo", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            Path arquivoPath = getArquivoPath(nomeArquivo);
            Files.deleteIfExists(arquivoPath);

        } catch(Exception e ){
            throw new StorageException("Não foi possível excluir o arquivo", e);
        }

    }

    private Path getArquivoPath(String nomeArquivo) {
        // retorna o caminho completo do arquivo
        return diretorioFotos.resolve(Path.of(nomeArquivo));
    }
}
