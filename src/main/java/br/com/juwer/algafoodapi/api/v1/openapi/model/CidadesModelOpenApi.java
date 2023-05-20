package br.com.juwer.algafoodapi.api.v1.openapi.model;

import br.com.juwer.algafoodapi.api.v1.model.dto.CidadeDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CidadesCollectionModel")
@Data
public class CidadesModelOpenApi {

    private CidadesModel _emmbedded;
    private Links _links;

    @ApiModel("CidadesModel")
    @Data
    public static class CidadesModel {
        private List<CidadeDTO> cidades;
    }
}
