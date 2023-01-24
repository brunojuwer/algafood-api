package br.com.juwer.algafoodapi.domain.model;

import lombok.Getter;

@Getter
public enum StatusPedido {
  
  CRIADO("Criado"),
  CONFIRMADO("Confirmado"),
  ENTREGE("Entrege"),
  CANCELADO("Cancelado");

  private final String descricao;
  StatusPedido(String descricao) {
    this.descricao = descricao;
  }
}
