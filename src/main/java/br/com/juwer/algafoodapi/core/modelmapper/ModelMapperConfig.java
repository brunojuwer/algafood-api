package br.com.juwer.algafoodapi.core.modelmapper;

import br.com.juwer.algafoodapi.api.model.dto.RestauranteDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.RestauranteDTOInput;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
                .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);

        return modelMapper;
    }
}
