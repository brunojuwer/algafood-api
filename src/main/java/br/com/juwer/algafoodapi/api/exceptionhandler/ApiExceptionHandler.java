package br.com.juwer.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

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
      EntidadeNaoEncontradaException e) {
    Problema problema = Problema.builder()
      .dataHora(LocalDateTime.now())
      .mensagem(e.getMessage()).build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(problema);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocioException(NegocioException e) {
    Problema problema = Problema.builder()
      .dataHora(LocalDateTime.now())
      .mensagem(e.getMessage()).build();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(problema);
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> handleEntidadeEmUsoException(NegocioException ex) {
    Problema problema = Problema.builder()
      .dataHora(LocalDateTime.now())
      .mensagem(ex.getMessage())
      .build();

    return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
  }
  
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    body = Problema.builder().dataHora(LocalDateTime.now()).mensagem(status.getReasonPhrase()).build();
    
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }
  
}
