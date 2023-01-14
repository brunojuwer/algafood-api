package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.RestauranteDTOInput;
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
}
