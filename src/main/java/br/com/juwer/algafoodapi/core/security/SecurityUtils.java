package br.com.juwer.algafoodapi.core.security;

import br.com.juwer.algafoodapi.domain.filter.PedidoFilter;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.repository.PedidoRespository;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class SecurityUtils {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private PedidoRespository pedidoRespository;


    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUsuarioId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("usuario_id");
    }

    public boolean gerenciaRestaurante(Long restauranteId) {
        return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
    }

    public boolean temPermissaoOuGerenciaRestaurante(Long restauranteId, String permissao) {
        return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId())
                || hasAuthority(permissao);
    }

    public boolean possuiPedidos(PedidoFilter filter) {
        if(filter.getClienteId() == null || !filter.getClienteId().equals(getUsuarioId())) {
            return false;
        }
        return pedidoRespository.existsPedido(getUsuarioId());
    }

    public boolean gerenciaRestaurantePedido(PedidoFilter filter) {
        if(filter.getRestauranteId() == null) {
            return false;
        }
        return this.gerenciaRestaurante(filter.getRestauranteId());
    }

    public boolean gerenciaPedido(String codigo) {
        Optional<Pedido> pedido = pedidoRespository.findByCodigo(codigo);
        return hasAuthority("SCOPE_WRITE") &&
                ( hasAuthority("GERENCIAR_PEDIDOS") ||
                    pedido.filter(value -> this.gerenciaRestaurante(value
                    .getRestaurante().getId())).isPresent());
    }

    public boolean gerenciaASiProprio(Long usuarioId) {
        return Objects.equals(usuarioId, getUsuarioId());
    }

    public boolean hasAuthority(String permissao) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(permissao));
    }

}
