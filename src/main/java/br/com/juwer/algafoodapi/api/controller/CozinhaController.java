package br.com.juwer.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/{cozinhaId}")
  public Cozinha buscar(@PathVariable Long cozinhaId){
    return cozinhaRepository.buscar(cozinhaId);
  }
}
