package br.com.juwer.algafoodapi.api.v2.openapi.model;

import br.com.juwer.algafoodapi.api.v1.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.api.v2.model.dto.CidadeDTOV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CidadesCollectionModel")
@Data
public class CidadesModelOpenApiV2 {

    private CidadesModelV2 _emmbedded;
    private Links _links;

    @ApiModel("CidadesModel")
    @Data
    public static class CidadesModelV2 {
        private List<CidadeDTOV2> cidades;
    }
}
