package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Autowired
    private EnvioEmailService envioEmailService;

    @Transactional
    public void confirmar(String codigo) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(codigo);
        pedido.confirmar();

        var mensagem = EnvioEmailService.Mensagem
                .builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado")
                .corpo("pedido-confirmado.html")
                .variavel("pedido", pedido)
                .destinatario(pedido.getCliente().getEmail())
                .build();

        envioEmailService.enviar(mensagem);
    }

    @Transactional
    public void cancelar(String codigo) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(codigo);
        pedido.cancelar();
    }

    @Transactional
    public void entregar(String codigo) {
        Pedido pedido = cadastroPedidoService.buscaOuFalha(codigo);
        pedido.entregar();
    }

}
