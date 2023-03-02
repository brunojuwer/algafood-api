package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.domain.filter.VendaDiariaFilter;
import br.com.juwer.algafoodapi.domain.model.dto.VendaDiaria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {

    @ApiOperation(value = "Consulta estatísticas de venda diárias", produces = "application/json, application/pdf")
    List<VendaDiaria> consultarVendasDiarias(
            VendaDiariaFilter filter,
            @ApiParam(value = "Passar o OffSet (EX: +03:00))") String timeOffSet);

    @ApiOperation(value = "Consulta estatísticas de venda diárias", hidden = true)
    ResponseEntity<byte[]> consultarVendasDiariasPdf(
            VendaDiariaFilter filter,
            String timeOffSet);
}
