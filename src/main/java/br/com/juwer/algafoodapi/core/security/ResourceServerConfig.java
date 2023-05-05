package br.com.juwer.algafoodapi.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
            .and()
                .cors()
            .and()
                .oauth2ResourceServer().jwt(); // alterar para jwt()
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // OBS: chave tem que ter mais de 256 bits
        var secretKey = new SecretKeySpec(
                "n26247586h4576h28475h60824576hdfxg807hdfg87".getBytes(),
                "HmacSHA256");

        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}
