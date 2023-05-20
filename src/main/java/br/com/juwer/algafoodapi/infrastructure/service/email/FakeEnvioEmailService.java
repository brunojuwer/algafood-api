package br.com.juwer.algafoodapi.infrastructure.service.email;

import br.com.juwer.algafoodapi.domain.service.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

    @Override
    public void enviar(Mensagem mensagem) {
        String corpo = processarTemplate(mensagem);
        log.info("\n" + "[FAKE E-MAIL] para {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}
