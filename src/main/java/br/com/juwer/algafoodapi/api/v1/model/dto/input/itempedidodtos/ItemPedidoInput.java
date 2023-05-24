package br.com.juwer.algafoodapi.api.v1.model.dto.input.itempedidodtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ItemPedidoInput {

    @NotNull
    private Long produtoId;

    @NotNull
    @Positive
    private Integer quantidade;

    private String observacao;
}
