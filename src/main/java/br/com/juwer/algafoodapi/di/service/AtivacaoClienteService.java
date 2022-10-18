package br.com.juwer.algafoodapi.di.service;

import br.com.juwer.algafoodapi.di.modelo.Cliente;
import br.com.juwer.algafoodapi.di.notificacao.Notificador;
import br.com.juwer.algafoodapi.di.notificacao.NotificadorEmail;

public class AtivacaoClienteService {

    private Notificador notificador;

    public AtivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void ativar(Cliente cliente){
        cliente.ativar();
        this.notificador.notificar(cliente, "Seu cadastro foi ativado!");
    }
}
