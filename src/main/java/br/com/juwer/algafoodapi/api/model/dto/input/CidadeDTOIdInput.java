package br.com.juwer.algafoodapi.api.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CidadeDTOIdInput {

    @NotNull
    private Long id;
}
