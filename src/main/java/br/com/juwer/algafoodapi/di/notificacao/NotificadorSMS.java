package br.com.juwer.algafoodapi.di.notificacao;

import br.com.juwer.algafoodapi.di.modelo.Cliente;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem){
        System.out.printf("Notificando %s através do número %s: %s\n",
                cliente.getNome(), cliente.getTelefone(), mensagem);
    }
}
