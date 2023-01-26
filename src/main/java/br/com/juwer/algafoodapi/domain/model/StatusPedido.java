package br.com.juwer.algafoodapi.domain.model;

import lombok.Getter;

import java.util.List;

@Getter
public enum StatusPedido {
  
  CRIADO("Criado"),
  CONFIRMADO("Confirmado", CRIADO),
  ENTREGUE("Entrege", CONFIRMADO),
  CANCELADO("Cancelado", CRIADO);

  private final String descricao;
  private final List<StatusPedido> statusAnteiores;

  StatusPedido(String descricao, StatusPedido... statusAnterioes) {
    this.descricao = descricao;
    this.statusAnteiores = List.of(statusAnterioes);
  }

  public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
    return !novoStatus.statusAnteiores.contains(this);
  }
}
