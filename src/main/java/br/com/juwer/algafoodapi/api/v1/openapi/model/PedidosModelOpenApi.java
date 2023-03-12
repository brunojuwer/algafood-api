package br.com.juwer.algafoodapi.api.v1.openapi.model;

import br.com.juwer.algafoodapi.api.v1.model.dto.PedidoResumoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("Pedidos")
@Data
public class PedidosModelOpenApi {

    private PedidosPagedModel _embedded;
    private Links _links;
    private PageModel page;

    @ApiModel("PedidosPagedModel")
    @Data
    public static class PedidosPagedModel {
        private List<PedidoResumoDTO> pedidos;
    }
}
