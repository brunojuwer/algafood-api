package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.filter.VendaDiariaFilter;

public interface VendaReportService {
    byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffSet);
}
