package br.com.juwer.algafoodapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
  private long id;
  
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

  private StatusPedido status;

  @ManyToOne
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
