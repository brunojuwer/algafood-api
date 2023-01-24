package br.com.juwer.algafoodapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Pedido {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Id
  private Long id;
  
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

  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> itens = new ArrayList<>();
}
