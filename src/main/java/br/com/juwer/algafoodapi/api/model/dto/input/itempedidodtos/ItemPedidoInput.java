package br.com.juwer.algafoodapi.api.model.dto.input.itempedidodtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ItemPedidoInput {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1)
    private Integer quantidade;

    private String observacao;
}
