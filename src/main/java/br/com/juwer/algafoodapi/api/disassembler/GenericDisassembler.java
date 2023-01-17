package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.CidadeDTOInput;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

public abstract class GenericDisassembler<I, D> {

    @Autowired
    protected ModelMapper modelMapper;

    private final Class<D> domainObject;

    @SuppressWarnings("unchecked")
    public GenericDisassembler() {
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
