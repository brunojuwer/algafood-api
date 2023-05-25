package br.com.juwer.algafoodapi.core.springdoc;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@SecurityScheme(
        name = "security_auth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
                authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
                tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
                scopes = {
                        @OAuthScope(name = "READ", description = "read scope"),
                        @OAuthScope(name = "WRITE", description = "write scope")
                }
        ))
)
public class SpringdocConfig {

    @Bean
    public GroupedOpenApi groupedOpenApiV1() {
        return GroupedOpenApi.builder()
                .group("Algafood V1")
                .packagesToScan("br.com.juwer.algafoodapi.api")
                .pathsToMatch("/v1/**")
                .addOpenApiCustomiser(openApi -> {
                    openApi
                        .info(new Info()
                        .title("AlgaFood API V1")
                        .description("Documentação da API Algafood")
                        .version("v0.0.1")
                        .license(new License()
                            .name("Apache 2.0")
                            .url("http://springdoc.com")));
//                            .components(new Components().schemas(
//                                    this.gerarSchemas()
//                            ));
                })
                .addOpenApiCustomiser(this.globalError())
                .build();
    }

    @Bean
    public GroupedOpenApi groupedOpenApiV2() {
        return GroupedOpenApi.builder()
                .group("Algafood V2")
                .packagesToScan("br.com.juwer.algafoodapi.api")
                .pathsToMatch("/v2/**")
                .addOpenApiCustomiser(openApi -> {
                    openApi
                    .info(new Info()
                        .title("AlgaFood API V2")
                        .description("Documentação da API Algafood")
                        .version("v0.0.2")
                        .license(new License()
                            .name("Apache 2.0")
                            .url("http://springdoc.com")))
                        .components(new Components().schemas(
                            this.gerarSchemas()
                        ));
                })
                .addOpenApiCustomiser(this.globalError())
                .build();
    }

    @Bean
    public OpenApiCustomiser globalError() {
        return openApi -> {
            openApi.getPaths().values()
                .forEach(pathItem -> pathItem.readOperationsMap()
                    .forEach(((httpMethod, operation) -> {

                        ApiResponses responses = operation.getResponses();

                        switch (httpMethod) {
                            case GET -> {
                                responses.addApiResponse("500", new ApiResponse().description("Internal Server Error"));
                                responses.addApiResponse("406", new ApiResponse().description("Not Acceptable"));
                                responses.addApiResponse("404", new ApiResponse().description("Not found"));
                            }
                            case POST -> {
                                responses.addApiResponse("500", new ApiResponse().description("Internal Server Error"));
                                responses.addApiResponse("400", new ApiResponse().description("Bad Request"));
                            }
                            case PUT -> {
                                responses.addApiResponse("404", new ApiResponse().description("Not found"));
                                responses.addApiResponse("500", new ApiResponse().description("Internal Server Error"));
                                responses.addApiResponse("400", new ApiResponse().description("Bad Request"));
                            }
                            case DELETE -> {
                                responses.addApiResponse("500", new ApiResponse().description("Internal Server Error"));
                                responses.addApiResponse("404", new ApiResponse().description("Not found"));
                            }
                            default -> {
                            }
                        }


                    })));
        };
    }


    private Map<String, Schema> gerarSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();

        Map<String, Schema> schemaProblem = ModelConverters.getInstance().read(Problem.class);
        Map<String, Schema> schemaProblemObject = ModelConverters.getInstance().read(Problem.Object.class);

        schemaMap.putAll(schemaProblem);
        schemaMap.putAll(schemaProblemObject);

        return schemaMap;
    }
}
