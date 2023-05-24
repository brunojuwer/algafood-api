package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.CidadeDTOInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface CidadeControllerSpringDoc {
    @Deprecated
    CollectionModel<CidadeDTO> listar();

    CidadeDTO buscar(Long cidadeId);

    CidadeDTO adicionar(CidadeDTOInput cidadeDTOInput);

    CidadeDTO atualizar(Long cidadeId, CidadeDTOInput cidadeDTOInput);

    void remover(Long cidadeId);
}
