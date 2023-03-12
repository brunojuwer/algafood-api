package br.com.juwer.algafoodapi.api.v1.assembler;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericAssembler <M, D> {

    @Autowired
    @Getter
    protected ModelMapper modelMapper;

    private final Class<M> modelObject;

    @SuppressWarnings("unchecked")
    public GenericAssembler() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.modelObject = (Class<M>) type.getActualTypeArguments()[0];
    }

    public M toModel(D domainObject) {
        return this.modelMapper.map(domainObject, this.modelObject);
    }

    public List<M> toCollectionModel(Collection<D> listOfDomainObjects) {
        return listOfDomainObjects.stream().map(this::toModel).collect(Collectors.toList());
    }
}
