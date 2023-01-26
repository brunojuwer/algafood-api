package br.com.juwer.algafoodapi.domain.model;

import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Pedido {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Id
  private Long id;

  private String codigo;
  
  @Column(nullable = false)
  private BigDecimal subTotal;

  @Column(nullable = false)
  private BigDecimal taxaFrete;

  @Column(nullable = false)
  private BigDecimal valorTotal;

  @CreationTimestamp
  @Column(nullable = false)
  private OffsetDateTime dataCriacao;

  private OffsetDateTime dataConfirmacao;
  private OffsetDateTime dataCancelamento;
  private OffsetDateTime dataEntrega;

  @Embedded
  private Endereco enderecoEntrega;

  @Enumerated(EnumType.STRING)
  private StatusPedido status = StatusPedido.CRIADO;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private FormaPagamento formaPagamento;

  @ManyToOne
  @JoinColumn(name = "usuario_cliente_id" ,nullable = false)
  private Usuario cliente;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Restaurante restaurante;

  @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
  private List<ItemPedido> itens = new ArrayList<>();

  public void calcularValorTotal() {
    getItens().forEach(ItemPedido::calcularPrecoTotal);

    this.subTotal = getItens().stream()
            .map(ItemPedido::getPrecoTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    this.valorTotal = this.subTotal.add(this.taxaFrete);
  }

  public void confirmar() {
    this.setStatus(StatusPedido.CONFIRMADO);
    this.setDataConfirmacao(OffsetDateTime.now());
  }

  public void cancelar() {
    this.setStatus(StatusPedido.CANCELADO);
    this.setDataCancelamento(OffsetDateTime.now());
  }

  public void entregar() {
    this.setStatus(StatusPedido.ENTREGUE);
    this.setDataEntrega(OffsetDateTime.now());
  }

  private void setStatus(StatusPedido novoStatus) {
    if(this.getStatus().naoPodeAlterarPara(novoStatus)){
      throw new NegocioException(String.format(
              "Status do pedido %d não pode ser alterado de %s para %s",
              this.id, this.getStatus().getDescricao(), novoStatus.getDescricao()
      ));
    }
    this.status = novoStatus;
  }

  @PrePersist
  private void setCodigo(){
    this.codigo = UUID.randomUUID().toString();
  }
}
