package br.com.juwer.algafoodapi.api.v2.disassembler;

import br.com.juwer.algafoodapi.api.v1.disassembler.GenericDisassembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.CidadeDTOInput;
import br.com.juwer.algafoodapi.api.v2.model.dtoinput.CidadeDTOInputV2;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.model.Estado;
import org.springframework.stereotype.Component;

@Component
public class CidadeDTODisassemblerV2 extends GenericDisassemblerV2<CidadeDTOInputV2, Cidade> {

    @Override
    public void copyToDomainObject(CidadeDTOInputV2 cidadeDTOInput, Cidade cidade) {
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeDTOInput, cidade);
    }
}
