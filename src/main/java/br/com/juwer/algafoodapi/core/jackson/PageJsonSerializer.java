package br.com.juwer.algafoodapi.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {

    @Override
    public void serialize(Page<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
            jgen.writeObjectField("conteudo", value.getContent());
            jgen.writeObjectField("tamanhoPorPagina", value.getSize());
            jgen.writeObjectField("elementosTotais", value.getTotalElements());
            jgen.writeObjectField("paginasTotais", value.getTotalPages());
            jgen.writeObjectField("numeroPagina", value.getNumber());
        jgen.writeEndObject();
    }
}
