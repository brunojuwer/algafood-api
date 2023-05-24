package br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UsuarioDTOInput {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;
}
