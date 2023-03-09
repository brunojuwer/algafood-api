package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.controller.GrupoController;
import br.com.juwer.algafoodapi.api.model.dto.GrupoDTO;
import br.com.juwer.algafoodapi.api.utils.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GrupoDTOAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public GrupoDTOAssembler() {
        super(GrupoController.class, GrupoDTO.class);
    }

    @Override
    public GrupoDTO toModel(Grupo grupo) {
        GrupoDTO grupoDTO = modelMapper.map(grupo, GrupoDTO.class);

        grupoDTO.add(hateoasAlgaLinks.linkToGrupos("grupos"));
        grupoDTO.add(hateoasAlgaLinks.linkToGrupo(grupoDTO.getId()));
        grupoDTO.add(hateoasAlgaLinks.linkToPermissoes(grupoDTO.getId(), "permissoes"));

        return grupoDTO;
    }

    @Override
    public CollectionModel<GrupoDTO> toCollectionModel(Iterable<? extends Grupo> entities) {
        return super.toCollectionModel(entities).add(hateoasAlgaLinks.linkToGrupos(IanaLinkRelations.SELF_VALUE));
    }
}
