package br.com.juwer.algafoodapi.listener;


import br.com.juwer.algafoodapi.di.notificacao.NivelUrgencia;
import br.com.juwer.algafoodapi.di.notificacao.Notificador;
import br.com.juwer.algafoodapi.di.notificacao.TipoDoNotificador;
import br.com.juwer.algafoodapi.di.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent cliente){
        notificador.notificar(cliente.getCliente(), "Seu cadastro no sistema está ativo!");
    }
}
