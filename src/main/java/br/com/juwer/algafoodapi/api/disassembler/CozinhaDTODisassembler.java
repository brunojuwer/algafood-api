package br.com.juwer.algafoodapi.api.disassembler;

import br.com.juwer.algafoodapi.api.model.dto.input.CozinhaDTOInput;
import br.com.juwer.algafoodapi.api.model.dto.input.CozinhaIdDTOInput;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDTODisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha convertDTOInputToCozinha(CozinhaDTOInput cozinhaDTOInput) {
        return modelMapper.map(cozinhaDTOInput, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaDTOInput cozinhaDTOInput, Cozinha cozinha){
        modelMapper.map(cozinhaDTOInput, cozinha);
    }
}
