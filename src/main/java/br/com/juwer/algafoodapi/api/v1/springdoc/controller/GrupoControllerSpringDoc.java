package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.GrupoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.GrupoDTOInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupo", description = "Gerencia os grupos")
public interface GrupoControllerSpringDoc {
    CollectionModel<GrupoDTO> listar();

    GrupoDTO buscar(Long grupoId);

    GrupoDTO adicionar(GrupoDTOInput grupoDTOInput);

    GrupoDTO atualizar(Long grupoId, GrupoDTOInput grupoDTOInput);

    void excluir(Long grupoId);
}
