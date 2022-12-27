package br.com.juwer.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler {

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

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<?> handleHttpMediaTypeNotSupportedException() {
    Problema problema = Problema.builder()
      .dataHora(LocalDateTime.now())
      .mensagem("Tipo de mídia não suportado").build();

    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
      .body(problema);
  }
}
