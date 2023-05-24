package br.com.juwer.algafoodapi.api.v1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "foto")
@Setter
@Getter
public class FotoProdutoDTO extends RepresentationModel<FotoProdutoDTO> {
    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
