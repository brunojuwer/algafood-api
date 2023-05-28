package br.com.juwer.algafoodapi.core.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiDeprecationHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().startsWith("/v1/cidades")) {
            response.addHeader("X-Algafood-Deprecated",
                    "Essa versão da API está depreciada e deixará de existir a partir de 01/01/2024."
                    + "Use a versão mais atual da API.");
        }

        return true;
    }
}
