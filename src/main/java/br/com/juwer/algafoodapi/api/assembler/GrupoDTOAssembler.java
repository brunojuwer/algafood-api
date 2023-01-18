package br.com.juwer.algafoodapi.api.assembler;

import br.com.juwer.algafoodapi.api.model.dto.GrupoDTO;
import br.com.juwer.algafoodapi.domain.model.Grupo;
import org.springframework.stereotype.Component;

@Component
public class GrupoDTOAssembler extends GenericAssembler<GrupoDTO, Grupo>{}
