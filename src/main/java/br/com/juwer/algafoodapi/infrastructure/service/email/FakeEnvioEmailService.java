package br.com.juwer.algafoodapi.infrastructure.service.email;

import br.com.juwer.algafoodapi.domain.service.EnvioEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FakeEnvioEmailService implements EnvioEmailService {

    @Autowired
    private ProcessarTemplateEmail processarTemplateEmail;

    @Override
    public void enviar(Mensagem mensagem) {
        String corpo = processarTemplateEmail.processarTemplate(mensagem);
        log.info("\n" + "[FAKE E-MAIL] para {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}
