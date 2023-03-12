package br.com.juwer.algafoodapi.api.v1.assembler;

import br.com.juwer.algafoodapi.api.v1.controller.CidadeController;
import br.com.juwer.algafoodapi.api.v1.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CidadeDTOAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    public CidadeDTOAssembler() {
        super(CidadeController.class, CidadeDTO.class);
    }

    @Override
    public CidadeDTO toModel(Cidade cidade) {
        CidadeDTO cidadeDTO = createModelWithId(cidade.getId(), cidade);
        modelMapper.map(cidade, cidadeDTO);

        cidadeDTO.add(hateoasAlgaLinks.linkToCidade());

        cidadeDTO.getEstado().add(hateoasAlgaLinks.linktoEstado(cidadeDTO.getEstado().getId()));

        return cidadeDTO;
    }

    @Override
    public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> entities) {
        return super.toCollectionModel(entities).add(hateoasAlgaLinks.linktoSelfCidades());
    }
}
