package br.com.juwer.algafoodapi.api.v2.controller;

import br.com.juwer.algafoodapi.api.v2.HateoasAlgaLinksV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v2",produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "security_auth")
@Tag(name = "RootEntryPointV2", description = "Ponto de entrada para os endpoints da V2")
public class RootEntryPointControllerV2 {

    @Autowired
    private HateoasAlgaLinksV2 hateoasAlgaLinks;

    @GetMapping
    @Operation(summary = "Lista todas os links da V2")
    public RootEntryPointModelV2 root() {
        var rootEntryPointModel = new RootEntryPointModelV2();
            rootEntryPointModel.add(hateoasAlgaLinks.linkToCidade());
            rootEntryPointModel.add(hateoasAlgaLinks.linkToCozinha());
        return rootEntryPointModel;
    }

    private static class RootEntryPointModelV2 extends RepresentationModel<RootEntryPointModelV2> {}
}
