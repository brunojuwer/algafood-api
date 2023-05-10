package br.com.juwer.algafoodapi.core.security.authorizationserver;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JwtCustomClaimsTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {

        if(oAuth2Authentication.getPrincipal() instanceof AuthUser) {
            var usuario = (AuthUser) oAuth2Authentication.getPrincipal();
            Map<String, Object> info = new HashMap<>();
            info.put("nome_completo", usuario.getFullName());
            info.put("usuario_id", usuario.getClientId());

            var oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
            oAuth2AccessToken.setAdditionalInformation(info);
        }

        return accessToken;
    }
}
