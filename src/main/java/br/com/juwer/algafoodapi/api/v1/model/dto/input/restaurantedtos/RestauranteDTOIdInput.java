package br.com.juwer.algafoodapi.api.v1.model.dto.input.restaurantedtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestauranteDTOIdInput {

    @NotNull
    private Long id;
}
