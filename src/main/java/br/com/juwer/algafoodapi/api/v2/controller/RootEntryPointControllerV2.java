package br.com.juwer.algafoodapi.api.v2.controller;

import br.com.juwer.algafoodapi.api.v2.HateoasAlgaLinksV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v2",produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointControllerV2 {

    @Autowired
    private HateoasAlgaLinksV2 hateoasAlgaLinks;

    @GetMapping
    public RootEntryPointModelV2 root() {
        var rootEntryPointModel = new RootEntryPointModelV2();
            rootEntryPointModel.add(hateoasAlgaLinks.linkToCidade());
            rootEntryPointModel.add(hateoasAlgaLinks.linkToCozinha());
        return rootEntryPointModel;
    }

    private static class RootEntryPointModelV2 extends RepresentationModel<RootEntryPointModelV2> {}
}
