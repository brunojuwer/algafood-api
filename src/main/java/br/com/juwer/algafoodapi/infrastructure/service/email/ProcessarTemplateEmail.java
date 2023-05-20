package br.com.juwer.algafoodapi.infrastructure.service.email;

import br.com.juwer.algafoodapi.domain.service.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class ProcessarTemplateEmail {

    @Autowired
    private Configuration freemarkerConfig;


    protected String processarTemplate(EnvioEmailService.Mensagem mensagem) {
        try {
            Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
            return FreeMarkerTemplateUtils
                    .processTemplateIntoString(template, mensagem.getVariaveis());

        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do email", e);
        }
    }
}
