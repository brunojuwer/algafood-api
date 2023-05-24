package br.com.juwer.algafoodapi.core.springdoc;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                })
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
                            .url("http://springdoc.com")));
                })
                .build();
    }
}
