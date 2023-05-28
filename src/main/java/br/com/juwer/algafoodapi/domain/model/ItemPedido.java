package br.com.juwer.algafoodapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class ItemPedido {
  
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Id
  private Long id;

  @Column(nullable = false)
  private Integer quantidade;

  @Column(nullable = false)
  private BigDecimal precoUnitario;

  @Column(nullable = false)
  private BigDecimal precoTotal;

  private String observacao;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Pedido pedido;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Produto produto;

  public void calcularPrecoTotal() {
    BigDecimal precoUnitario = this.getPrecoUnitario();
    Integer quantidade = this.getQuantidade();

    if (precoUnitario == null) {
      precoUnitario = BigDecimal.ZERO;
    }

    if (quantidade == null) {
      quantidade = 0;
    }

    this.setPrecoTotal(precoUnitario.multiply(new BigDecimal(quantidade)));
  }
}
