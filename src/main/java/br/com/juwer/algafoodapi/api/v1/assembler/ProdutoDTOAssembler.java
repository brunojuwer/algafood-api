package br.com.juwer.algafoodapi.api.v1.assembler;

import br.com.juwer.algafoodapi.api.v1.controller.RestauranteProdutosController;
import br.com.juwer.algafoodapi.api.v1.model.dto.ProdutoDTO;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDTOAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public ProdutoDTOAssembler() {
        super(RestauranteProdutosController.class, ProdutoDTO.class);
    }

    @Override
    public ProdutoDTO toModel(Produto produto) {
        ProdutoDTO produtoDTO = modelMapper.map(produto, ProdutoDTO.class);

        produtoDTO.add(hateoasAlgaLinks
                .linkToProduto(produto.getRestaurante().getId(), produto.getId()));
        produtoDTO.add(hateoasAlgaLinks
                .linkToProduto(produto.getRestaurante().getId(), "produtos"));

        produtoDTO.add(hateoasAlgaLinks.linkToFoto(produto.getRestaurante().getId(), produtoDTO.getId(), "foto"));

        return produtoDTO;
    }
}
