package br.com.juwer.algafoodapi.core.openapi;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

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
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())
                .apiInfo(this.apiInfo())
                .tags(new Tag("Cidades", "Gerencia as cidades"),
                        new Tag("Cozinha", "Gerencia as cozinhas"));
    }

    private List<Response> globalGetResponseMessages(){
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do servidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .description("Recurso não econtrado")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (Erro do cliente)")
                        .build()
        );
    }
    private List<Response> globalPostResponseMessages(){
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do servidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("O corpo do payload possui algum erro")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .description("Recurso não econtrado")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Tipo de mídia não suportado")
                        .build()
        );
    }
    private List<Response> globalPutResponseMessages(){
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do servidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (Erro do cliente)")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .description("Recurso não econtrado")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Tipo de mídia não suportado")
                        .build()
        );
    }

    private List<Response> globalDeleteResponseMessages(){
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do servidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_FOUND.value()))
                        .description("Recurso não econtrado")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (Erro do cliente)")
                        .build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Algafood API")
                .description("API aberta para cliente e restaurantes")
                .version("1")
                .contact(new Contact("Bruno", "http://www.juwer.com.br", "bruno@juwer.com.br"))
                .build();
    }
}
