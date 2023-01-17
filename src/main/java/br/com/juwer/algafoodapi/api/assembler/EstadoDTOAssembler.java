package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.EstadoDTO;
import br.com.juwer.algafoodapi.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstadoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoDTO convertToEstadoDTO(Estado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }

    public List<EstadoDTO> convertToDTOList(List<Estado> estados) {
        return estados.stream().map(this::convertToEstadoDTO).collect(Collectors.toList());
    }
}
