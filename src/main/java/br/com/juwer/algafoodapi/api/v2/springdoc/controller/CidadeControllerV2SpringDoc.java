package br.com.juwer.algafoodapi.api.v2.springdoc.controller;

import br.com.juwer.algafoodapi.api.v2.model.dto.CidadeDTOV2;
import br.com.juwer.algafoodapi.api.v2.model.dtoinput.CidadeDTOInputV2;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface CidadeControllerV2SpringDoc {
    CollectionModel<CidadeDTOV2> listar();

    CidadeDTOV2 buscar(Long cidadeId);

    CidadeDTOV2 adicionar(CidadeDTOInputV2 cidadeDTOInput);

    CidadeDTOV2 atualizar(Long cidadeId, CidadeDTOInputV2 cidadeDTOInput);

    void remover(Long cidadeId);
}
