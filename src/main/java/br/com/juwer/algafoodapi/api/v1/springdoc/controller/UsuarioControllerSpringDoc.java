package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.UsuarioDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos.UsuarioDTOInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos.UsuarioDTOInputPost;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos.UsuarioDTOInputSenha;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuario", description = "Gerencia os usuarios")
public interface UsuarioControllerSpringDoc {
    CollectionModel<UsuarioDTO> listar();

    UsuarioDTO buscar(Long usuarioId);

    UsuarioDTO adicionar(UsuarioDTOInputPost usuarioDTOInputPost);

    UsuarioDTO atualizar(Long usuarioId,
                         UsuarioDTOInput usuarioDTOInput);

    void senha(Long usuarioId, UsuarioDTOInputSenha usuarioDTOInputSenha);

    void excluir(Long usuarioId);
}
