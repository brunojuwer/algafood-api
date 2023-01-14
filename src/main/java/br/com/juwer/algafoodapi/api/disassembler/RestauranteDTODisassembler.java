package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.RestauranteDTOInput;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTODisassembler {

    public Restaurante convertDTOInToRestaurante(RestauranteDTOInput restauranteDTOIn) {
        Restaurante restaurante = new Restaurante();
        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteDTOIn.getCozinha().getId());

        restaurante.setNome(restauranteDTOIn.getNome());
        restaurante.setTaxaFrete(restauranteDTOIn.getTaxaFrete());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
