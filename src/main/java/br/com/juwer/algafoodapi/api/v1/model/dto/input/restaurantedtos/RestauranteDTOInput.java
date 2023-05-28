package br.com.juwer.algafoodapi.api.v1.model.dto.input.restaurantedtos;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.CozinhaIdDTOInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.EnderecoDTOInput;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
