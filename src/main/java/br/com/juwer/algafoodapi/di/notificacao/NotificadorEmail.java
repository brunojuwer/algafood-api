package br.com.juwer.algafoodapi.di.notificacao;

import br.com.juwer.algafoodapi.di.modelo.Cliente;

public class NotificadorEmail implements Notificador {

    private boolean caixaAlta;
    private String hostServidorSMTP;

    public NotificadorEmail(String hostServidorSMTP) {
        this.hostServidorSMTP = hostServidorSMTP;
    }

    @Override
    public void notificar(Cliente cliente, String mensagem){

        if(caixaAlta) mensagem = mensagem.toUpperCase();

        System.out.printf("Notificando %s através do e-mail %s usando o servidor %s: %s\n",
                cliente.getNome(), cliente.getEmail(), this.hostServidorSMTP, mensagem);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}
