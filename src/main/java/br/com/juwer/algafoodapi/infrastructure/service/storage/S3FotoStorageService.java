package br.com.juwer.algafoodapi.infrastructure.service.storage;

import br.com.juwer.algafoodapi.core.storage.StorageProperties;
import br.com.juwer.algafoodapi.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class S3FotoStorageService implements FotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        String keyName = getCaminhoArquivo(nomeArquivo);
        String bucketName = storageProperties.getS3().getBucket();

        URL url =  amazonS3.getUrl(bucketName, keyName);

        return FotoRecuperada.builder().url(url.toString()).build();
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        try {
            String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
            var objectMetaData = new ObjectMetadata();
            objectMetaData.setContentType(novaFoto.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    caminhoArquivo,
                    novaFoto.getInputStream(),
                    objectMetaData
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possivel salvar o arquivo em AWS S3", e);
        }
    }


    @Override
    public void remover(String nomeArquivo) {
        try {
            String bucketName = storageProperties.getS3().getBucket();
            String keyName = getCaminhoArquivo(nomeArquivo);

            amazonS3.deleteObject(new DeleteObjectRequest(bucketName, keyName));
        } catch(Exception e){
            throw new StorageException("Não foi possivel remover o arquivo em AWS S3", e);
        }
    }

    private String getCaminhoArquivo(String nomeArquivo) {
        return String.format("%s/%s", storageProperties.getS3().getDirectory(), nomeArquivo);
    }
}
