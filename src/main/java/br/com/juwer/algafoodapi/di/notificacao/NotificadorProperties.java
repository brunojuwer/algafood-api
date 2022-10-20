package br.com.juwer.algafoodapi.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificacao.email") // pegar apenas o que começar com esse prefixo
public class NotificadorProperties {

    private String hostServidor; // vai receber o valor do host
    private Integer portaServidor; // vai receber o valor da porta

    public String getHostServidor() {
        return hostServidor;
    }
    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }

    public Integer getPortaServidor() {
        return portaServidor;
    }
    public void setPortaServidor(Integer portaServidor) {
        this.portaServidor = portaServidor;
    }
}
