package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.PermissoesDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.PermissaoDTO;
import br.com.juwer.algafoodapi.api.v1.openapi.controller.PermissaoControllerOpenAPI;
import br.com.juwer.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController implements PermissaoControllerOpenAPI {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissoesDTOAssembler permissoesDTOAssembler;

    @Override
    @GetMapping
    public CollectionModel<PermissaoDTO> listar() {
        return permissoesDTOAssembler.toCollectionModel(permissaoRepository.findAll());
    }
}
