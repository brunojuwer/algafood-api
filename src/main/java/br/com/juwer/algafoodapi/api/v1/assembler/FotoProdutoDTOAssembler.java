package br.com.juwer.algafoodapi.api.v1.assembler;

import br.com.juwer.algafoodapi.api.v1.controller.RestauranteProdutoFotoController;
import br.com.juwer.algafoodapi.api.v1.model.dto.FotoProdutoDTO;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoDTOAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public FotoProdutoDTOAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoDTO.class);
    }

    @Override
    public FotoProdutoDTO toModel(FotoProduto foto) {
        FotoProdutoDTO fotoProdutoDTO = modelMapper.map(foto, FotoProdutoDTO.class);

        fotoProdutoDTO.add(hateoasAlgaLinks
                .linkToFoto(foto.getRestauranteId(), foto.getProduto().getId(), IanaLinkRelations.SELF_VALUE));

        fotoProdutoDTO.add(hateoasAlgaLinks
                .linkToProduto(foto.getRestauranteId(), foto.getProduto().getId(),"produto"));

        return fotoProdutoDTO;
    }
}
