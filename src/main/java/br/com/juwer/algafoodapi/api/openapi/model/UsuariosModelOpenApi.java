package br.com.juwer.algafoodapi.api.openapi.model;

import br.com.juwer.algafoodapi.api.model.dto.UsuarioDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("UsuariosColletion")
@Data
public class UsuariosModelOpenApi {

    private UsuariosModel _embedded;
    private Links _links;

    @ApiModel("UsuariosModel")
    @Data
    public static class UsuariosModel {
        private List<UsuarioDTO> usuarios;
    }
}
