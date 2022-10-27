package br.com.juwer.algafoodapi.jpa;

import br.com.juwer.algafoodapi.AlgafoodApiApplication;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.infrastructure.repository.RestauranteRepositoryImpl;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepositoryImpl repositoryImpl = applicationContext.getBean(RestauranteRepositoryImpl.class);

        List<Restaurante> restaurantes = repositoryImpl.listar();

        restaurantes.forEach(restaurante -> System.out.println(restaurante.getNome() + " - " + restaurante.getCozinha().getNome()));
    }
}
