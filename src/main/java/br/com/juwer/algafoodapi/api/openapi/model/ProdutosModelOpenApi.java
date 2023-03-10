package br.com.juwer.algafoodapi.api.openapi.model;

import br.com.juwer.algafoodapi.api.model.dto.ProdutoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("ProdutosColletion")
@Data
public class ProdutosModelOpenApi {

    private ProdutosModel _embedded;
    private Links _links;

    @ApiModel("ProdutosModel")
    @Data
    public static class ProdutosModel {
        private List<ProdutoDTO> produtos;
    }
}
