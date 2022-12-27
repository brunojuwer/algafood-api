package br.com.juwer.algafoodapi.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
  
  private static final long serialVersionUID = 1L;

  public EntidadeNaoEncontradaException(String message) {
    super(message);
  }
}
