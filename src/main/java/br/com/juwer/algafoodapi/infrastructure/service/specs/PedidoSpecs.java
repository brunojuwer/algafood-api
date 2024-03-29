package br.com.juwer.algafoodapi.infrastructure.service.specs;

import br.com.juwer.algafoodapi.domain.filter.PedidoFilter;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if(Pedido.class.equals(query.getResultType())){
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("cliente");
            }

            if(filter.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), filter.getClienteId()));
            }
            if(filter.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante").get("id"), filter.getRestauranteId()));
            }
            if(filter.getApartirData() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getApartirData()));
            }
            if(filter.getAteData() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getAteData()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
