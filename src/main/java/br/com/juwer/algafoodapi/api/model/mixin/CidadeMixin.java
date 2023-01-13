package br.com.juwer.algafoodapi.api.model.mixin;

import br.com.juwer.algafoodapi.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CidadeMixin {
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
