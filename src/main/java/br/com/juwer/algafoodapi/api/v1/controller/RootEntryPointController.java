package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
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
@RequestMapping(path = "/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "security_auth")
@Tag(name = "RootEntryPoint", description = "Ponto de entrada para os endpoints da API")
public class RootEntryPointController {

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    @GetMapping
    @Operation(summary = "Lista todos os links para a V1")
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
            rootEntryPointModel.add(hateoasAlgaLinks.linkToEstatisticas("estatisticas"));
        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {}
}
