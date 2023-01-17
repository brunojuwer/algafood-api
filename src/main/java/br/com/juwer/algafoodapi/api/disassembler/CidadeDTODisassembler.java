package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.CidadeDTOInput;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade convertToDomainModel(CidadeDTOInput cidadeDTOInput) {
        return modelMapper.map(cidadeDTOInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeDTOInput cidadeDTOInput, Cidade cidade) {
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeDTOInput, cidade);
    }
}
