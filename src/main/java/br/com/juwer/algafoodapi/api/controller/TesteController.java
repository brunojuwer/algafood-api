package br.com.juwer.algafoodapi.api.controller;

import static br.com.juwer.algafoodapi.infrastructure.specs.RestauranteSpecs.comFreteGratis;
import static br.com.juwer.algafoodapi.infrastructure.specs.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.CozinhaRepository;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
  
  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @GetMapping("/cozinhas/por-nome")
  public List<Cozinha> cozinhasPorNome(@RequestParam String nome) {
    return cozinhaRepository.findAllByNomeContaining(nome);
  }

  @GetMapping("/restaurantes/por-taxa")
  public List<Restaurante> restauranteTaxaEntre(BigDecimal taxaInicial, BigDecimal taxaFinal) {
    return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
  }

  @GetMapping("/restaurantes/por-nome")
  public List<Restaurante> restaurantesPorNome(String nome, Long cozinhaId){
    return restauranteRepository.consultarPorNome(nome, cozinhaId);
  }

  @GetMapping("/restaurantes/por-nome/taxa")
  public List<Restaurante> restaurantesPorNomeETaxa(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
    return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
  }

  @GetMapping("/restaurantes/com-frete-gratis")
  public List<Restaurante> restaurantesPorFreteGratis(String nome){
    return restauranteRepository
      .findAll(comFreteGratis().and(comNomeSemelhante(nome)));
  }
}
