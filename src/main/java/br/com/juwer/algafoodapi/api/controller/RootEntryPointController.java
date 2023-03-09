package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.utils.HateoasAlgaLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    @GetMapping
    public RootEntryPointModel root() {
        var rootEntryPointModel = new RootEntryPointModel();
            rootEntryPointModel.add(hateoasAlgaLinks.linkToRestaurante().withRel("restaurantes"));
            rootEntryPointModel.add(hateoasAlgaLinks.linkToCozinha().withRel("cozinhas"));
            rootEntryPointModel.add(hateoasAlgaLinks.linkToCidade().withRel("cidades"));
            rootEntryPointModel.add(hateoasAlgaLinks.linkToEstados().withRel("estados"));
            rootEntryPointModel.add(hateoasAlgaLinks.linkToFormasPagamento(false));
            rootEntryPointModel.add(hateoasAlgaLinks.linkToPedidos().withRel("pedidos"));
            rootEntryPointModel.add(hateoasAlgaLinks.linkToClientes(false));
            rootEntryPointModel.add(hateoasAlgaLinks.linkToGrupos("grupos"));
            rootEntryPointModel.add(hateoasAlgaLinks.linkToPermissoes().withRel("permissoes"));


        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {}
}
