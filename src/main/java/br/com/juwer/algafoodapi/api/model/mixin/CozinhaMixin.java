package br.com.juwer.algafoodapi.api.model.mixin;

import br.com.juwer.algafoodapi.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class CozinhaMixin {
    @JsonIgnore
    private List<Restaurante> restaurantes;
}
