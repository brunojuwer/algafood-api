package br.com.juwer.algafoodapi.api.v1.openapi.model;

import br.com.juwer.algafoodapi.api.v1.model.dto.EstadoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("EstadosCollection")
@Data
public class EstadosModelOpenApi {

    private EstadosModel _emmbedded;
    private Links _links;

    @ApiModel("EstadosModel")
    @Data
    public static class EstadosModel {
        private List<EstadoDTO> estados;
    }
}
