package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CozinhaDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaDTO convertToDTO(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    public List<CozinhaDTO> convertToDtoList(List<Cozinha> cozinhas) {
        return cozinhas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
