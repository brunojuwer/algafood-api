package br.com.juwer.algafoodapi.core.storage;

import br.com.juwer.algafoodapi.domain.service.FotoStorageService;
import br.com.juwer.algafoodapi.infrastructure.storage.LocalFotoStorageService;
import br.com.juwer.algafoodapi.infrastructure.storage.S3FotoStorageService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    public AmazonS3 amazonS3() {
        String accessIdKey = storageProperties.getS3().getIdKeyAccess();
        String secret = storageProperties.getS3().getSecret();
        Regions region = storageProperties.getS3().getRegion();

        var credentials = new BasicAWSCredentials(accessIdKey, secret);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    @Bean
    public FotoStorageService fotoStorageService() {
        if(StorageProperties.TipoStorage.S3.equals(storageProperties.getTipo())){
            return new S3FotoStorageService();
        }
        return new LocalFotoStorageService();
    }
}
