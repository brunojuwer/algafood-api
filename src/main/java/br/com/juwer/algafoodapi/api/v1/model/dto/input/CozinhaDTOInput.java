package br.com.juwer.algafoodapi.api.v1.model.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaDTOInput {

    @Schema(example = "Japonesa")
    @NotBlank
    private String nome;
}
