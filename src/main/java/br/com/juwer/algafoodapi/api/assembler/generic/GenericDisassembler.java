package br.com.juwer.algafoodapi.api.assembler.generic;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

public abstract class GenericDisassembler<I, D> {

    @Autowired
    @Getter
    protected ModelMapper modelMapper;

    private final Class<D> domainObject;

    @SuppressWarnings("unchecked")
    public GenericDisassembler() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();

        this.domainObject = (Class<D>) type.getActualTypeArguments()[1];
    }

    public D toModel(I inputObject) {
        return this.modelMapper.map(inputObject, this.domainObject);
    }

    public void copyToDomainObject(I inputObject, D domainObject) {
        this.modelMapper.map(inputObject, domainObject);
    }
}
