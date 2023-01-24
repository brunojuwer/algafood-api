package br.com.juwer.algafoodapi.api.model.dto.input.pedidosdto;

import br.com.juwer.algafoodapi.api.model.dto.input.EnderecoDTOInput;
import br.com.juwer.algafoodapi.api.model.dto.input.formapagamentodtos.FormaPagamentoDTOIdInput;
import br.com.juwer.algafoodapi.api.model.dto.input.itempedidodtos.ItemPedidoInput;
import br.com.juwer.algafoodapi.api.model.dto.input.restaurantedtos.RestauranteDTOIdInput;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
