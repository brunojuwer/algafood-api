package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.PermissoesDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.PermissaoDTO;
import br.com.juwer.algafoodapi.api.v1.springdoc.controller.PermissaoControllerSpringDoc;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import br.com.juwer.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/permissoes")
public class PermissaoController implements PermissaoControllerSpringDoc {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissoesDTOAssembler permissoesDTOAssembler;

    @Override
    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping
    public CollectionModel<PermissaoDTO> listar() {
        return permissoesDTOAssembler.toCollectionModel(permissaoRepository.findAll());
    }
}
