package br.com.juwer.algafoodapi.api.v1.model.dto.input.usuariodtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioDTOInputPost extends UsuarioDTOInput{

    @NotBlank
    private String senha;
}
