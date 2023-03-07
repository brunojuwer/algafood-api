package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.controller.CidadeController;
import br.com.juwer.algafoodapi.api.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CidadeDTOAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

    @Autowired
    ModelMapper modelMapper;

    public CidadeDTOAssembler() {
        super(CidadeController.class, CidadeDTO.class);
    }

    @Override
    public CidadeDTO toModel(Cidade domainObject) {
        return this.modelMapper.map(domainObject, CidadeDTO.class);
    }

    public List<CidadeDTO> toCollectionModel(Collection<Cidade> listOfDomainObjects) {
        return listOfDomainObjects.stream().map(this::toModel).collect(Collectors.toList());
    }
}
