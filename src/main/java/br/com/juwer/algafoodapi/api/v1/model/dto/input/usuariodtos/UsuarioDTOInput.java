package br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Setter
@Getter
public class UsuarioDTOInput {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;
}
