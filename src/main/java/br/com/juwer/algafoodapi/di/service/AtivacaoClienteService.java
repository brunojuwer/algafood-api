package br.com.juwer.algafoodapi.di.service;

import br.com.juwer.algafoodapi.di.modelo.Cliente;
import br.com.juwer.algafoodapi.di.notificacao.NivelUrgencia;
import br.com.juwer.algafoodapi.di.notificacao.Notificador;
import br.com.juwer.algafoodapi.di.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AtivacaoClienteService {

    @TipoDoNotificador(NivelUrgencia.URGENTE)
    @Autowired
    private Notificador notificador;

    public void ativar(Cliente cliente){
        cliente.ativar();

       this.notificador.notificar(cliente, "Seu cadastro foi ativado!");
    }
}
