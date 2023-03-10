package br.com.juwer.algafoodapi.api.openapi.model;

import br.com.juwer.algafoodapi.api.model.dto.PermissaoDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PermissoesColletion")
@Data
public class PermissoesModelOpenApi {

    private PermissoesModel _embedded;
    private Links _links;

    @ApiModel("PermissoesModel")
    @Data
    public static class PermissoesModel {
        private List<PermissaoDTO> permissoes;
    }

}
