package br.com.juwer.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.juwer.algafoodapi.api.model.CozinhasRepresentationModel;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
  
  @Autowired
  private CozinhaRepository cozinhaRepository;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Cozinha> listar(){
    return cozinhaRepository.listar();
  }

  @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
  public CozinhasRepresentationModel listarXML(){
    return new CozinhasRepresentationModel(cozinhaRepository.listar());
  }

  @GetMapping("/{cozinhaId}")
  public ResponseEntity<Cozinha>  buscar(@PathVariable Long cozinhaId){
    Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

    if(cozinha != null){
      return ResponseEntity.ok().body(cozinha);
    }

    return ResponseEntity.notFound().build();
  }
}
