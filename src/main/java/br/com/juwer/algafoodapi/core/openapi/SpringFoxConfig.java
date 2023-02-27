package br.com.juwer.algafoodapi.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.juwer.algafoodapi.api"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.ant("/restaurantes/*")) é possivel selecionar os caminhos
                .build()
                .apiInfo(this.apiInfo())
                .tags(new Tag("Cidades", "Gerencia as cidades"),
                        new Tag("Cozinha", "Gerencia as cozinhas"));
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Algafood API")
                .description("API aberta para cliente e restaurantes")
                .version("1")
                .contact(new Contact("Bruno", "http://www.juwer.com.br", "bruno@juwer.com.br"))
                .build();
    }
}
