package br.com.juwer.algafoodapi.api.v1.assembler;

import br.com.juwer.algafoodapi.api.v1.controller.EstadoController;
import br.com.juwer.algafoodapi.api.v1.model.dto.EstadoDTO;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class EstadoDTOAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public EstadoDTOAssembler() {
        super(EstadoController.class, EstadoDTO.class);
    }

    @Override
    public EstadoDTO toModel(Estado estado) {
        EstadoDTO estadoDTO = modelMapper.map(estado, EstadoDTO.class);

        estadoDTO.add(hateoasAlgaLinks.linktoEstado(estadoDTO.getId()));
        estadoDTO.add(hateoasAlgaLinks.linkToEstados());

        return estadoDTO;
    }

    @Override
    public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
                .add(hateoasAlgaLinks.linkToSelfEstados());
    }
}
