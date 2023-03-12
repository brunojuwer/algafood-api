package br.com.juwer.algafoodapi.api.v1.openapi.model;


import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteBasicoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("RestaurantesColletion")
@Data
public class RestaurantesModelOpenApi {

    private RestaurantesModel _embedded;
    private Links _links;

    @ApiModel("RestaurantesModel")
    @Data
    public static class RestaurantesModel {
        private List<RestauranteBasicoDTO> restaurantes;
    }
}
