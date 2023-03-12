package br.com.juwer.algafoodapi.api.v1.openapi.model;

import br.com.juwer.algafoodapi.api.v1.model.dto.GrupoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("GruposColletion")
@Data
public class GruposModelOpenApi {

    private GruposModel _embedded;
    private Links _links;

    @ApiModel("GruposModel")
    @Data
    public static class GruposModel {
        private List<GrupoDTO> grupos;
    }
}
