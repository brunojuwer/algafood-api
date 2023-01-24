package br.com.juwer.algafoodapi.domain.exception;

public class FormaPagamentoNaoEncontrada extends EntidadeNaoEncontradaException {

    public FormaPagamentoNaoEncontrada(String message) {
        super(message);
    }

    public FormaPagamentoNaoEncontrada(Long formaPagamentoId) {
        this(String.format("Não existe um cadastro de forma de pagamento com código: %d", formaPagamentoId));
    }

    public FormaPagamentoNaoEncontrada(Long formaPagamentoId, Long restauranteId) {
        this(String.format("O restaurante com código %d não possui a forma de pagamento com código: %d",
                restauranteId, formaPagamentoId));
    }
}
