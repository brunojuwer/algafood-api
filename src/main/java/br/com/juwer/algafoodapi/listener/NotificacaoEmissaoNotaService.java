package br.com.juwer.algafoodapi.listener;

import br.com.juwer.algafoodapi.di.service.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoEmissaoNotaService {

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent cliente){
        System.out.println("Caro cliente " + cliente.getCliente().getNome() + " sua nota foi emitida!");
    }
}
