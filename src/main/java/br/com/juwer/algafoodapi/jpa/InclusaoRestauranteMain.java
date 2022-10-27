package br.com.juwer.algafoodapi.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.juwer.algafoodapi.AlgafoodApiApplication;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.infrastructure.repository.RestauranteRepositoryImpl;

public class InclusaoRestauranteMain {
  public static void main(String[] args) {
    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    
    RestauranteRepositoryImpl cadastroCozinha = applicationContext.getBean(RestauranteRepositoryImpl.class);

    Restaurante cozinha1 = new Restaurante();
    Restaurante cozinha2 = new Restaurante();

    cozinha1.setNome("Americana");
    cozinha2.setNome("Italiana");

    cadastroCozinha.salvar(cozinha1);
    cadastroCozinha.salvar(cozinha2);
}
}
