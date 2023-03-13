package br.com.juwer.algafoodapi.api.v2.assembler;

import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.api.v1.controller.CozinhaController;
import br.com.juwer.algafoodapi.api.v1.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.v2.HateoasAlgaLinksV2;
import br.com.juwer.algafoodapi.api.v2.controller.CozinhaControllerV2;
import br.com.juwer.algafoodapi.api.v2.model.dto.CozinhaDTOV2;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDTOAssemblerV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaDTOV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HateoasAlgaLinksV2 hateoasAlgaLinks;

    public CozinhaDTOAssemblerV2() {
        super(CozinhaControllerV2.class, CozinhaDTOV2.class);
    }

    @Override
    public CozinhaDTOV2 toModel(Cozinha cozinha) {
        CozinhaDTOV2 cozinhaDTO = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaDTO);

        cozinhaDTO.add(hateoasAlgaLinks.linkToCozinha());

        return cozinhaDTO;
    }
}
