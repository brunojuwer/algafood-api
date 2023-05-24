package br.com.juwer.algafoodapi.api.v1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("cidades")
@Getter
@Setter
public class CidadeResumoDTO extends RepresentationModel<CidadeResumoDTO> {

    private Long id;
    private String nome;
    private String estado;
}
