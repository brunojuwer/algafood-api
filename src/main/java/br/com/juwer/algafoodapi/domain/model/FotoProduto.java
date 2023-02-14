package br.com.juwer.algafoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoProduto {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
}
