package br.com.juwer.algafoodapi.domain.exception;

public class EntidadeEmUsoException extends NegocioException {
  
  private static final long serialVersionUID = 1L;

  public EntidadeEmUsoException(String message) {
    super(message);
  }
}
