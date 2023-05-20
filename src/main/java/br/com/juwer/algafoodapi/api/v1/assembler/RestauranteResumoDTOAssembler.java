package br.com.juwer.algafoodapi.api.v1.assembler;

import br.com.juwer.algafoodapi.api.v1.controller.RestauranteController;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteResumoDTO;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteResumoDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteResumoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public RestauranteResumoDTOAssembler() {
        super(RestauranteController.class, RestauranteResumoDTO.class);
    }

    @Override
    public RestauranteResumoDTO toModel(Restaurante restaurante) {
        RestauranteResumoDTO restauranteResumoDTO = modelMapper.map(restaurante, RestauranteResumoDTO.class);

        restauranteResumoDTO.add(hateoasAlgaLinks.linkToRestaurante(restauranteResumoDTO.getId()));
        return restauranteResumoDTO;
    }

    @Override
    public CollectionModel<RestauranteResumoDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities)
                .removeLinks().add(hateoasAlgaLinks.listToRestauranteResumo("restaurantes"));
    }
}
