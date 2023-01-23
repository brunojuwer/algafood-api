package br.com.juwer.algafoodapi.api.model.dto.input.restaurantedtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestauranteDTOIdInput {

    @NotNull
    private Long id;
}
