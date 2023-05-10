package br.com.juwer.algafoodapi.core.security.authorizationserver;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
