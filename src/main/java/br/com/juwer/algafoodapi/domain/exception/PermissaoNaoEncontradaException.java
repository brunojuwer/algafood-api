package br.com.juwer.algafoodapi.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException{
    public PermissaoNaoEncontradaException(String message) {
        super(message);
    }

    public PermissaoNaoEncontradaException(Long permissaoId) {
        this(String.format("Não existe um cadastro de permissão com código: %d", permissaoId));
    }
}
