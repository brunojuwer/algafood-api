package br.com.juwer.algafoodapi.core.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {

    private Local local = new Local();
    private S3 s3 = new S3();
    private TipoStorage tipo = TipoStorage.LOCAL;

    public enum TipoStorage {
        S3, LOCAL;
    }

    @Getter
    @Setter
    public class Local {
        private Path diretorioFotos;
    }

    @Getter
    @Setter
    public class S3 {
        private String idKeyAccess;
        private String secret;
        private String bucket;
        private Regions region;
        private String directory;
    }
}
