package br.com.juwer.algafoodapi;

import br.com.juwer.algafoodapi.di.modelo.Cliente;
import br.com.juwer.algafoodapi.di.notificacao.NotificadorEmail;
import br.com.juwer.algafoodapi.di.service.AtivacaoClienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}
}
