package br.com.juwer.algafoodapi.api.model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import br.com.juwer.algafoodapi.domain.model.Cozinha;
import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "cozinhas")
@Data
public class CozinhasRepresentationModel {
 
  // gera automaticamente o construtor
  @JacksonXmlProperty(localName = "cozinha")
  @NonNull
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<Cozinha> cozinhas;
}
