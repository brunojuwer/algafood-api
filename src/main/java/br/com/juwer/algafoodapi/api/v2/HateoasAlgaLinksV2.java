package br.com.juwer.algafoodapi.api.v2;

import br.com.juwer.algafoodapi.api.v2.controller.CidadeControllerV2;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class HateoasAlgaLinksV2 {

    public Link linkToCidade() {
        return linkTo(methodOn(CidadeControllerV2.class).listar())
                .withRel("cidades");
    }

    public Link linktoSelfCidades() {
        return linkTo(CidadeControllerV2.class).withSelfRel();
    }

    public Link linkToCidade(Long cidadeId) {
        return linkTo(methodOn(CidadeControllerV2.class)
                .buscar(cidadeId)).withSelfRel();
    }
}
