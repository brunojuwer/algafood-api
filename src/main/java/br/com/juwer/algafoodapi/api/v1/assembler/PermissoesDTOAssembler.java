package br.com.juwer.algafoodapi.api.v1.assembler;

import br.com.juwer.algafoodapi.api.v1.controller.PermissaoController;
import br.com.juwer.algafoodapi.api.v1.model.dto.PermissaoDTO;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PermissoesDTOAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;


    public PermissoesDTOAssembler() {
        super(PermissaoController.class, PermissaoDTO.class);
    }

    @Override
    public PermissaoDTO toModel(Permissao permissao) {
        PermissaoDTO permissaoDTO = modelMapper.map(permissao, PermissaoDTO.class);
        return permissaoDTO;
    }

    @Override
    public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends Permissao> entities) {
        return super.toCollectionModel(entities).add(hateoasAlgaLinks.linkToPermissoes());
    }
}
