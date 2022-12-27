package br.com.juwer.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontrada(
      EntidadeNaoEncontradaException ex, WebRequest request) {
    
    return handleExceptionInternal(
        ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocioException(
        NegocioException ex, WebRequest request) {
    
    return handleExceptionInternal(
        ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> handleEntidadeEmUsoException(NegocioException ex, WebRequest request) {

    return handleExceptionInternal(
          ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
  
  @Override
  protected ResponseEntity<Object> handleExceptionInternal
    (Exception ex, @Nullable Object body, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
    
    if (body == null) {
      body = Problema.builder().dataHora(LocalDateTime.now())
          .mensagem(status.getReasonPhrase()).build();
    } else if (body instanceof String)  {
      body = Problema.builder().dataHora(LocalDateTime.now())
          .mensagem((String) body).build();
    }
    
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }
 
}
