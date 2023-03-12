package br.com.juwer.algafoodapi.api.v2.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

public abstract class GenericDisassemblerV2<I, D> {

    @Autowired
    protected ModelMapper modelMapper;

    private final Class<D> domainObject;

    @SuppressWarnings("unchecked")
    public GenericDisassemblerV2() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();

        this.domainObject = (Class<D>) type.getActualTypeArguments()[1];
    }

    public D toDomainObject(I inputObject) {
        return this.modelMapper.map(inputObject, this.domainObject);
    }

    public void copyToDomainObject(I inputObject, D domainObject) {
        this.modelMapper.map(inputObject, domainObject);
    }
}
