package br.com.juwer.algafoodapi.api.v1.openapi.model;

import br.com.juwer.algafoodapi.api.v1.model.dto.FormaPagamentoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("FormasPagamentoCollection")
@Data
public class FormasPagamentoModelOpenApi {

    private FormasPagamentoModel _embedded;
    private Links _links;

    @ApiModel("FormasPagamentoModel")
    @Data
    public static class FormasPagamentoModel {
        private List<FormaPagamentoDTO> formasPagamento;
    }
}
