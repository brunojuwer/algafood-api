package br.com.juwer.algafoodapi.infrastructure.service;

import br.com.juwer.algafoodapi.domain.filter.VendaDiariaFilter;
import br.com.juwer.algafoodapi.domain.model.Pedido;
import br.com.juwer.algafoodapi.domain.model.StatusPedido;
import br.com.juwer.algafoodapi.domain.model.dto.VendaDiaria;
import br.com.juwer.algafoodapi.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @Autowired
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        var predicates = new ArrayList<Predicate>();

        var functionDateDataCriacao = builder.function("date", Date.class, root.get("dataCriacao"));
        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        query.select(selection);
        query.groupBy(functionDateDataCriacao);

        if(filter.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filter.getRestauranteId()));
        }
        if(filter.getApartirData() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getApartirData()));
        }
        if(filter.getAteData() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getAteData()));
        }

        predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

         query.where(predicates.toArray(new Predicate[0]));

        return manager.createQuery(query).getResultList();
    }
}
