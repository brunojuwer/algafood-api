package br.com.juwer.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntidadeNaoEncontradaException extends ResponseStatusException {
  
  private static final long serialVersionUID = 1L;

  public EntidadeNaoEncontradaException(HttpStatus status, String message) {
    super(status, message);
  }

  public EntidadeNaoEncontradaException(String message) {
    this(HttpStatus.NOT_FOUND, message);
  }

  
}
