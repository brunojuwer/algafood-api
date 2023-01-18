package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.RestauranteDTOInput;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTODisassembler extends GenericDisassembler<RestauranteDTOInput, Restaurante>{

    @Override
    public void copyToDomainObject(RestauranteDTOInput restauranteDTOInput, Restaurante restaurante){
        // serve para resolver:
        // org.springframework.orm.jpa.JpaSystemException:
        // identifier of an instance of br.com.juwer.algafoodapi.domain.model.Cozinha was altered from 1 to 2
        restaurante.setCozinha(new Cozinha());

        if(restaurante.getEndereco().getCidade().getId() != null) {
            restaurante.getEndereco().setCidade(new Cidade());
        }

        modelMapper.map(restauranteDTOInput, restaurante);
    }
}
