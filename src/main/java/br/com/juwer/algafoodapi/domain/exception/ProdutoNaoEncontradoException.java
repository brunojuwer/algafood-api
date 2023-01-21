package br.com.juwer.algafoodapi.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }

    public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
        this(String.format(
                "No restaurante com código %d não existe um produto com código %d",
                    restauranteId, produtoId ));
    }
}
