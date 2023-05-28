package br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioDTOInputSenha {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String senha;
}
