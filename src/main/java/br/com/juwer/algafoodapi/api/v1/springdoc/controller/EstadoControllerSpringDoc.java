package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.EstadoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.EstadoDTOInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estado", description = "Gerencia os estados")
public interface EstadoControllerSpringDoc {

    CollectionModel<EstadoDTO> listar();

    EstadoDTO buscar(Long estadoId);

    EstadoDTO adicionar(EstadoDTOInput estadoDTOInput);

    EstadoDTO atualizar(Long estadoId, EstadoDTOInput estadoDTOInput);

    void excluir(Long estadoId);
}
