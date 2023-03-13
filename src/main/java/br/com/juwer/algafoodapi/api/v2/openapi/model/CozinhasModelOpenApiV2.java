package br.com.juwer.algafoodapi.api.v2.openapi.model;

import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.v1.openapi.model.PageModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel(value = "CozinhasCollection")
@Data
public class CozinhasModelOpenApiV2 {

    private CozinhasPagedModelV2 _embedded;
    private Links _links;
    private PageModel page;

    @ApiModel("CozinhasPagedModel")
    @Data
    public static class CozinhasPagedModelV2 {
        private List<CozinhaDTO> cozinhas;
    }
}