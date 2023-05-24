package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.controller.EstatisticasController;
import br.com.juwer.algafoodapi.domain.filter.VendaDiariaFilter;
import br.com.juwer.algafoodapi.domain.model.dto.VendaDiaria;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "security_auth")
public interface EstatisticasControllerSpringDoc {

    EstatisticasController.VendaDiariaLink vendaDiariaLink();

    List<VendaDiaria> consultarVendasDiarias(
            VendaDiariaFilter filter, String timeOffSet);

    ResponseEntity<byte[]> consultarVendasDiariasPdf(
            VendaDiariaFilter filter,
            String timeOffSet);
}
