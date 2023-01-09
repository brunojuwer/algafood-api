package br.com.juwer.algafoodapi;

import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	CadastroCozinhaService cadastroCozinhaService;

	@Test
	void deveTestarCadastroCozinhaComSucesso() {
		// cenário
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Japonesa");

		// Ação
		cozinha = cadastroCozinhaService.salvar(cozinha);

		// Validação
		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}

	@Test
	public void testarCadastroCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		ConstraintViolationException erroEsperado =
				Assertions.assertThrows(ConstraintViolationException.class, () -> {
					cadastroCozinhaService.salvar(novaCozinha);
				});

		assertThat(erroEsperado).isNotNull();
	}

}
