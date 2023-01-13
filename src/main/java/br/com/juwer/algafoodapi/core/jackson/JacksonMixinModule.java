package br.com.juwer.algafoodapi.core.jackson;

import br.com.juwer.algafoodapi.api.model.mixin.RestauranteMixin;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JacksonMixinModule () {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
    }

}
