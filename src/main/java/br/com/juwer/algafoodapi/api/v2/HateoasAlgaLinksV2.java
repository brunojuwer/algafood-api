package br.com.juwer.algafoodapi.api.v2;

import br.com.juwer.algafoodapi.api.v2.controller.CidadeControllerV2;
import br.com.juwer.algafoodapi.api.v2.controller.CozinhaControllerV2;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class HateoasAlgaLinksV2 {

    public static final TemplateVariables pageVariables = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
    );

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

    public Link linkToCozinha() {
        String cozinhasUrl = linkTo(CozinhaControllerV2.class).toUri().toString();
        return Link.of(UriTemplate.of(cozinhasUrl, pageVariables) , "cozinhas");
    }
}
