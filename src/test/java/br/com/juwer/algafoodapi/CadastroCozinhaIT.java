package br.com.juwer.algafoodapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aspectj.lang.annotation.Before;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import javax.swing.text.AbstractDocument;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

	@Autowired
	private Flyway flyway;
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "/cozinhas";
		RestAssured.port = port;
		flyway.migrate();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured
			.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveconterApenas4Cozinhas_QuandoConsultarCozinhas(){
		RestAssured
			.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.body("", Matchers.hasSize(4)) // verifica se tem quatro itens no banco
				.body("nome", Matchers.hasItems("Brasileira", "Tailandesa")); // verifica se tem esse nomes
	}

	@Test
	public void testDeveRetornarStatus201_QuandoCadastrarCozinha(){
		RestAssured
			.given()
				.body("{\"nome\": \"Japonesa\"}")
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
	}
}
