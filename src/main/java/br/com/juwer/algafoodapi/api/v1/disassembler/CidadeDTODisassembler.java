package br.com.juwer.algafoodapi.api.v1.disassembler;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.CidadeDTOInput;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.model.Estado;
import org.springframework.stereotype.Component;

@Component
public class CidadeDTODisassembler extends GenericDisassembler<CidadeDTOInput, Cidade>{

    @Override
    public void copyToDomainObject(CidadeDTOInput cidadeDTOInput, Cidade cidade) {
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeDTOInput, cidade);
    }
}
