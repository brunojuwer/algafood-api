package br.com.juwer.algafoodapi.api.v1.assembler;

import br.com.juwer.algafoodapi.api.v1.controller.RestauranteController;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteBasicoDTO;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteBasicoDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public RestauranteBasicoDTOAssembler() {
        super(RestauranteController.class, RestauranteBasicoDTO.class);
    }

    @Override
    public RestauranteBasicoDTO toModel(Restaurante restaurante) {
        RestauranteBasicoDTO restauranteBasicoDTO = modelMapper.map(restaurante, RestauranteBasicoDTO.class);

        restauranteBasicoDTO.add(hateoasAlgaLinks.linkToRestaurante(restauranteBasicoDTO.getId()));
        restauranteBasicoDTO.getCozinha()
                .add(hateoasAlgaLinks.linkToCozinha(restauranteBasicoDTO.getCozinha().getId()));

        return restauranteBasicoDTO;
    }

    @Override
    public CollectionModel<RestauranteBasicoDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
        return super.toCollectionModel(entities).add(hateoasAlgaLinks.linkToRestaurante());
    }
}
