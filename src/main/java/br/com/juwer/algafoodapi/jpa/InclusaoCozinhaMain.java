package br.com.juwer.algafoodapi.jpa;

import br.com.juwer.algafoodapi.AlgafoodApiApplication;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class InclusaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha1 = new Cozinha();
        Cozinha cozinha2 = new Cozinha();

        cozinha1.setNome("Americana");
        cozinha2.setNome("Italiana");

        cadastroCozinha.salvar(cozinha1);
        cadastroCozinha.salvar(cozinha2);
    }
}
