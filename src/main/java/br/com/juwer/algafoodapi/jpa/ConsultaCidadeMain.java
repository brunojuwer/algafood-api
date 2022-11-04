package br.com.juwer.algafoodapi.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import br.com.juwer.algafoodapi.AlgafoodApiApplication;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.repository.CidadeRepository;
import br.com.juwer.algafoodapi.infrastructure.repository.CidadeRepositoryImpl;

public class ConsultaCidadeMain {
  public static void main(String[] args) {
    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    CidadeRepository cadastroCidade = applicationContext.getBean(CidadeRepositoryImpl.class);

    List<Cidade> cidades = cadastroCidade.listar();

    cidades.forEach(cidade -> System.out.println(cidade.getNome()));
}
}
