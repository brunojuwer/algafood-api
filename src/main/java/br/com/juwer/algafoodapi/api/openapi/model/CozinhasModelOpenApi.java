package br.com.juwer.algafoodapi.api.openapi.model;

import br.com.juwer.algafoodapi.api.model.dto.CozinhaDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;

import java.util.List;

@ApiModel(value = "CozinhasCollection")
@Data
public class CozinhasModelOpenApi {

    private CozinhasPagedModel _embedded;
    private Links _links;
    private PageModel page;

    @ApiModel("CozinhasPagedModel")
    @Data
    public static class CozinhasPagedModel {
        private List<CozinhaDTO> cozinhas;
    }
}