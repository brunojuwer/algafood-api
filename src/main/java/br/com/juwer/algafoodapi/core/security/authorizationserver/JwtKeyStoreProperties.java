package br.com.juwer.algafoodapi.core.security.authorizationserver;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties("algafood.keystore")
public class JwtKeyStoreProperties {

    @NotNull
    private Resource location;

    @NotBlank
    private String keyStorePass;

    @NotBlank
    private String keyPairAlias;
}
