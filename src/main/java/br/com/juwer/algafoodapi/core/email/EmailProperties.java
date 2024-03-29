package br.com.juwer.algafoodapi.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

    @NotNull
    private String remetente;
    private Implementacao impl = Implementacao.FAKE;
    private Sandbox sandbox = new Sandbox();

    @Getter
    @Setter
    public class Sandbox {
        private String destinatario;
    }

    public enum Implementacao {
        FAKE, SMTP, SANDBOX
    }
}
