package br.com.juwer.algafoodapi.api.model.dto.input.restaurantedtos;

import br.com.juwer.algafoodapi.api.model.dto.input.CozinhaIdDTOInput;
import br.com.juwer.algafoodapi.api.model.dto.input.EnderecoDTOInput;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteDTOInput {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdDTOInput cozinha;

    @NotNull
    @Valid
    private EnderecoDTOInput endereco;
}