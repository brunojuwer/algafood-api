package br.com.juwer.algafoodapi.api.v1.assembler;

import br.com.juwer.algafoodapi.api.v1.controller.RestauranteController;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteDTO;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTOAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public RestauranteDTOAssembler() {
        super(RestauranteController.class, RestauranteDTO.class);
    }

    @Override
    public RestauranteDTO toModel(Restaurante restaurante) {
        RestauranteDTO restauranteDTO = modelMapper.map(restaurante, RestauranteDTO.class);

        restauranteDTO.add(hateoasAlgaLinks.linkToRestaurante(restauranteDTO.getId()));
        restauranteDTO.getCozinha().add(hateoasAlgaLinks.linkToCozinha(restauranteDTO.getCozinha().getId()));
        if(restauranteDTO.getEndereco() != null) {
            restauranteDTO.getEndereco().getCidade()
                    .add(hateoasAlgaLinks.linkToCidade(restauranteDTO.getEndereco().getCidade().getId()));
        }
        restauranteDTO.add(hateoasAlgaLinks.listToRestauranteResumo("restaurantes"));
        restauranteDTO.add(hateoasAlgaLinks.linkToFormasPagamentoRestaurante(restauranteDTO.getId()));
        restauranteDTO.add(hateoasAlgaLinks.linkToUsuariosRestaurante(restauranteDTO.getId()));
        restauranteDTO.add(hateoasAlgaLinks.linkToProduto(restauranteDTO.getId(), "produtos"));

        restauranteDTO.add(hateoasAlgaLinks
                .linkToAbrirOuFecharRestaurante(restaurante.getId(), restauranteDTO.getAberto()));
        restauranteDTO.add(hateoasAlgaLinks
                .linkToAtivarOuInativarRestaurante(restaurante.getId(), restauranteDTO.getAtivo()));

        return restauranteDTO;
    }
}
