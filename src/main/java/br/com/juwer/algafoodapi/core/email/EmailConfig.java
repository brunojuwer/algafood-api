package br.com.juwer.algafoodapi.core.email;

import br.com.juwer.algafoodapi.domain.service.EnvioEmailService;
import br.com.juwer.algafoodapi.infrastructure.service.email.FakeEnvioEmailService;
import br.com.juwer.algafoodapi.infrastructure.service.email.SandboxEnvioEmailService;
import br.com.juwer.algafoodapi.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        return switch (emailProperties.getImpl()) {
            case FAKE -> new FakeEnvioEmailService();
            case SMTP -> new SmtpEnvioEmailService();
            case SANDBOX -> new SandboxEnvioEmailService();
        };
    }
}
