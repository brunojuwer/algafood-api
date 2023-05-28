package br.com.juwer.algafoodapi.infrastructure.service.email;

import br.com.juwer.algafoodapi.core.email.EmailProperties;
import br.com.juwer.algafoodapi.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SmtpEnvioEmailService {//implements EnvioEmailService {

    @Autowired
    protected JavaMailSender mailSender;

    @Autowired
    protected EmailProperties emailProperties;

    @Autowired
    protected ProcessarTemplateEmail processarTemplateEmail;

//    @Override
//    public void enviar(Mensagem mensagem) {
//        try {
//            MimeMessage mimeMessage = criarMimeMessage(mensagem);
////            mailSender.send(mimeMessage);
//        } catch (Exception e) {
//            throw new EmailException("Não foi possível enviar o email", e);
//        }
//    }

//    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
//
//        String corpo = processarTemplateEmail.processarTemplate(mensagem);
////        MimeMessage mimeMessage = mailSender.createMimeMessage();
////        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
//        helper.setFrom(emailProperties.getRemetente());
//        helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
//        helper.setSubject(mensagem.getAssunto());
//        helper.setText(corpo, true);
//
//        return mimeMessage;
//    }


}
