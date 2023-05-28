package br.com.juwer.algafoodapi.api.v1.model.dto.input.pedidosdto;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.EnderecoDTOInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.formapagamentodtos.FormaPagamentoDTOIdInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.itempedidodtos.ItemPedidoInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.restaurantedtos.RestauranteDTOIdInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class PedidoDTOInput {

    @Valid
    @NotNull
    private RestauranteDTOIdInput restaurante;

    @Valid
    @NotNull
    private FormaPagamentoDTOIdInput formaPagamento;

    @Valid
    @NotNull
    private EnderecoDTOInput enderecoEntrega;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInput> itens = new ArrayList<>();

}
