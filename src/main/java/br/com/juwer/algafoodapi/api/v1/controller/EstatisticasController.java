package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.openapi.controller.EstatisticasControllerOpenApi;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.filter.VendaDiariaFilter;
import br.com.juwer.algafoodapi.domain.model.dto.VendaDiaria;
import br.com.juwer.algafoodapi.domain.service.VendaQueryService;
import br.com.juwer.algafoodapi.domain.service.VendaReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private VendaReportService vendaReportService;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    @GetMapping
    public VendaDiariaLink vendaDiariaLink() {
        var vendasDiarias = new VendaDiariaLink();
        vendasDiarias.add(hateoasAlgaLinks.linkToVendasDiarias());

        return vendasDiarias;
    }

    @Override
    @GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiaria> consultarVendasDiarias(
            VendaDiariaFilter filter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
        return vendaQueryService.consultarVendasDiarias(filter, timeOffSet);
    }

    @Override
    @GetMapping(value = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultarVendasDiariasPdf(
            VendaDiariaFilter filter,
            @RequestParam(required = false, defaultValue = "+00:00") String timeOffSet) {
        byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filter, timeOffSet);

        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf"); // informa para o navegador do cliente que deve baixar algo

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .headers(headers)
                .body(bytesPdf);
    }

    public static class VendaDiariaLink extends RepresentationModel<VendaDiariaLink> {}
}