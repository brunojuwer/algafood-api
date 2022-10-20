package br.com.juwer.algafoodapi.di.notificacao;

import br.com.juwer.algafoodapi.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    @Autowired
    private NotificadorProperties notificadorProperties;

    @Override
    public void notificar(Cliente cliente, String mensagem){

        System.out.println("HOST: " + notificadorProperties.getHostServidor()
                + "\nPORTA: " + notificadorProperties.getPortaServidor());

        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
