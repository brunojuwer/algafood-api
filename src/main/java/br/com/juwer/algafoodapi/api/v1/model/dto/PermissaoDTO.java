package br.com.juwer.algafoodapi.api.v1.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissoes")
@Getter
@Setter
public class PermissaoDTO extends RepresentationModel<PermissaoDTO> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "EDITAR_COZINHAS")
    private String nome;

    @ApiModelProperty(example = "Permite editar cozinhas")
    private String descricao;
}
