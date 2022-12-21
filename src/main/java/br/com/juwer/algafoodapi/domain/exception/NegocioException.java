package br.com.juwer.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;

  public NegocioException(String message) {
    super(message);
  }

  public NegocioException(String message, Throwable causa) {
    super(message, causa);
  }
}
