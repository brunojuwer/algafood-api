package br.com.juwer.algafoodapi.core.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiRetirementHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().startsWith("/v1/cidades")) {
            response.setStatus(HttpStatus.GONE.value());
            return false;
        }

        return true;
    }
}
