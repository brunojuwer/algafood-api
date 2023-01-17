package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.EstadoDTOIdInput;
import br.com.juwer.algafoodapi.api.model.dto.input.EstadoDTOInput;
import br.com.juwer.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoDTODIsassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Estado convertToDomainModel(EstadoDTOInput estadoDTOInput) {
        return modelMapper.map(estadoDTOInput, Estado.class);
    }

    public void copyToDomainModel(EstadoDTOIdInput estadoDTOIdInput, Estado estado) {
        modelMapper.map(estadoDTOIdInput, estado);
    }
}
