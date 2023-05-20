package br.com.juwer.algafoodapi;

import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.CozinhaRepository;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;
import br.com.juwer.algafoodapi.utils.DataBaseCleaner;
import br.com.juwer.algafoodapi.utils.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroRestauranteIT {

	private static final int ID_RESTAURANTE_INEXISTENTE = 100;
	private Restaurante restauranteJapaFood;
	private int quantidadeRestaurantesCadastrados;
	private String jsonCorretoRestauranteJapones;

	@Autowired
	private DataBaseCleaner dataBaseCleaner;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "/restaurantes";
		RestAssured.port = port;

		jsonCorretoRestauranteJapones = ResourceUtils.getContentFromResource(
				"/json/correto/restaurante-JapaFood.json");

		dataBaseCleaner.clearTables();
		prepararDados();
	}

	private void prepararDados() {
		Cozinha cozinhaJaponesa = new Cozinha();
		cozinhaJaponesa.setNome("Japonesa");
		cozinhaRepository.save(cozinhaJaponesa);

		restauranteJapaFood = new Restaurante();
		restauranteJapaFood.setNome("JapaFood");
		restauranteJapaFood.setTaxaFrete(BigDecimal.valueOf(9.90));
		restauranteJapaFood.setCozinha(cozinhaJaponesa);
		restauranteRepository.save(restauranteJapaFood);

		quantidadeRestaurantesCadastrados = (int) restauranteRepository.count();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarRestaurante() {
		RestAssured
			.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveconterApenasXRestaurantes_QuandoConsultarRestaurantes(){
		RestAssured
			.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.body("", Matchers.hasSize(quantidadeRestaurantesCadastrados));
	}

	@Test
	public void testDeveRetornarStatus201_QuandoCadastrarRestaurante(){
		RestAssured
			.given()
				.body(jsonCorretoRestauranteJapones)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarRestauranteExistente(){
		RestAssured
			.given()
				.pathParam("restauranteId", restauranteJapaFood.getId())
				.accept(ContentType.JSON)
			.when()
				.get("/{restauranteId}")
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", Matchers.equalTo(restauranteJapaFood.getNome()));
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarRestauranteInexistente(){
		RestAssured
			.given()
				.pathParam("restauranteId", ID_RESTAURANTE_INEXISTENTE)
				.accept(ContentType.JSON)
			.when()
				.get("/{restauranteId}")
			.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
