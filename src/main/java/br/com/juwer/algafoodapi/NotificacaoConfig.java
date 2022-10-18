package br.com.juwer.algafoodapi;

import br.com.juwer.algafoodapi.di.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfig {

    @Bean
    public NotificadorEmail notificadorEmail(){
        NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.juwer.com.br");
        notificadorEmail.setCaixaAlta(true);

        return notificadorEmail;
    }
}
