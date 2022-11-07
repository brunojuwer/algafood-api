package br.com.juwer.algafoodapi.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
  
  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroRestauranteService restauranteService;

  @GetMapping
  public List<Restaurante> listar(){
    return restauranteRepository.findAll();
  }

  @GetMapping("/{restauranteId}")
  public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
    Optional<Restaurante>  restaurante = restauranteRepository
      .findById(restauranteId);
    
    if (restaurante.isPresent()){
      return ResponseEntity.ok(restaurante.get());
    }
    
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {

    try {
      
      restaurante = restauranteService.salvar(restaurante);

      return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

    } catch (EntidadeNaoEncontradaException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


  @PutMapping("/{restauranteId}")
  public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
    @RequestBody Restaurante restaurante) {
      
    Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
        
      try {
        
        if(restauranteAtual.isPresent()) {

          BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");
          Restaurante restauranteSalvo = restauranteService.salvar(restauranteAtual.get());
          return ResponseEntity.ok(restauranteSalvo);

        }        
        return ResponseEntity.notFound().build();
        
      } catch (EntidadeNaoEncontradaException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      } 
  }


  @DeleteMapping("/{restauranteId}")
  public ResponseEntity<?> excluir(@PathVariable Long restauranteId) {
    
    try {
      restauranteService.excluir(restauranteId);
      return ResponseEntity.noContent().build();
    } catch (EntidadeNaoEncontradaException e) {
  			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
  			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
  }

  @PatchMapping("/{restauranteId}")
  public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
    @RequestBody Map<String, Object> campos) {
    
    Optional<Restaurante>  restauranteAtual = restauranteRepository.findById(restauranteId);
    
      if(restauranteAtual.isEmpty()) {
        return ResponseEntity.notFound().build();
      }

      merge(campos, restauranteAtual.get());

    return atualizar(restauranteId, restauranteAtual.get());
  }

  private void merge(Map<String, Object> camposOrigem, Restaurante restaranteDestino){
    ObjectMapper objectMapper = new ObjectMapper();
    Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

    camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
      
      Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
      field.setAccessible(true);

      Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

      ReflectionUtils.setField(field, restaranteDestino, novoValor);

    });
  }
}