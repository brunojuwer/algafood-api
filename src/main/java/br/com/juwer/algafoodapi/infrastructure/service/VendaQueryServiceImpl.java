package br.com.juwer.algafoodapi.infrastructure.service;

import br.com.juwer.algafoodapi.domain.filter.VendaDiariaFilter;
import br.com.juwer.algafoodapi.domain.model.dto.VendaDiaria;
import br.com.juwer.algafoodapi.domain.service.VendaQueryService;

import java.util.List;

public class VendaQueryServiceImpl implements VendaQueryService {

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
        return null;
    }
}
