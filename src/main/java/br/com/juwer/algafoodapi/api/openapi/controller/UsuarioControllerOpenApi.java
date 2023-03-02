package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.model.dto.UsuarioDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.usuariodtos.UsuarioDTOInput;
import br.com.juwer.algafoodapi.api.model.dto.input.usuariodtos.UsuarioDTOInputPost;
import br.com.juwer.algafoodapi.api.model.dto.input.usuariodtos.UsuarioDTOInputSenha;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

    @ApiOperation("Lista os usuários")
    List<UsuarioDTO> listar();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsuarioDTO buscar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Usuário cadastrado"),
    })
    UsuarioDTO adicionar(
        @ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true)
        UsuarioDTOInputPost usuarioDTOInputPost);


    @ApiOperation("Atualiza um usuário por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuário atualizado"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    UsuarioDTO atualizar(
        @ApiParam(value = "ID do usuário", example = "1", required = true)
        Long usuarioId,
        @ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados", required = true)
        UsuarioDTOInput usuarioDTOInput);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Senha alterada com sucesso"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void senha(
        @ApiParam(value = "ID do usuário", example = "1", required = true)
        Long usuarioId,
        @ApiParam(name = "corpo", value = "Representação de uma nova senha", required = true)
        UsuarioDTOInputSenha usuarioDTOInputSenha);


    @ApiOperation("Excluir usuário pelo ID")
    @ApiResponses({
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
    })
    void excluir(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);
}
