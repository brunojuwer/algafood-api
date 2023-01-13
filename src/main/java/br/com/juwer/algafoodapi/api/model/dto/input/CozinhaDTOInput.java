package br.com.juwer.algafoodapi.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CozinhaDTOInput {

    @NotNull
    private Long id;
}
