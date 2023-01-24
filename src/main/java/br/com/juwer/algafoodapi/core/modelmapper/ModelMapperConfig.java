package br.com.juwer.algafoodapi.core.modelmapper;

import br.com.juwer.algafoodapi.api.model.dto.EnderecoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.itempedidodtos.ItemPedidoInput;
import br.com.juwer.algafoodapi.domain.model.Endereco;
import br.com.juwer.algafoodapi.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));


        var enderecoToEnderecoModel = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);
        enderecoToEnderecoModel.<String>addMapping(
                src -> src.getCidade().getEstado().getNome(),
                (dest, value) -> dest.getCidade().setEstado(value)
        );

        return modelMapper;
    }
}
