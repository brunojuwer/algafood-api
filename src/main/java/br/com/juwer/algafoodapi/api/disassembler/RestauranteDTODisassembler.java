package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.RestauranteDTOInput;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante convertDTOInputToRestaurante(RestauranteDTOInput restauranteDTOInput) {
        return modelMapper.map(restauranteDTOInput, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteDTOInput restauranteDTOInput, Restaurante restaurante){
        // serve para resolver:
        // org.springframework.orm.jpa.JpaSystemException:
        // identifier of an instance of br.com.juwer.algafoodapi.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());

        modelMapper.map(restauranteDTOInput, restaurante);
    }
}
