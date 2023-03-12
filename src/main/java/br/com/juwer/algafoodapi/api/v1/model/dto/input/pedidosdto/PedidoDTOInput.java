package br.com.juwer.algafoodapi.api.v1.model.dto.input.pedidosdto;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.EnderecoDTOInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.formapagamentodtos.FormaPagamentoDTOIdInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.itempedidodtos.ItemPedidoInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.restaurantedtos.RestauranteDTOIdInput;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "1", required = true)
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
