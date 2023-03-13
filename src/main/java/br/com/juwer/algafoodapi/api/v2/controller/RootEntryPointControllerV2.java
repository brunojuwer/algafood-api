package br.com.juwer.algafoodapi.api.v2.controller;

import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.api.v2.HateoasAlgaLinksV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "RootEntryPoint")
@RestController
@RequestMapping(path = "/v2",produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointControllerV2 {

    @Autowired
    private HateoasAlgaLinksV2 hateoasAlgaLinks;

    @ApiOperation("Consulta links da V2")
    @GetMapping
    public RootEntryPointModelV2 root() {
        var rootEntryPointModel = new RootEntryPointModelV2();
            rootEntryPointModel.add(hateoasAlgaLinks.linkToCidade());
            rootEntryPointModel.add(hateoasAlgaLinks.linkToCozinha());
        return rootEntryPointModel;
    }

    private static class RootEntryPointModelV2 extends RepresentationModel<RootEntryPointModelV2> {}
}
