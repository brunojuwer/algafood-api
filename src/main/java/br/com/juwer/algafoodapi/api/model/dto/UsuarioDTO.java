package br.com.juwer.algafoodapi.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "usuarios")
@Getter
@Setter
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

    @ApiModelProperty(example = "2")
    private Long id;

    @ApiModelProperty(example = "João")
    private String nome;

    @ApiModelProperty(example = "joao@email.com")
    private String email;
}
