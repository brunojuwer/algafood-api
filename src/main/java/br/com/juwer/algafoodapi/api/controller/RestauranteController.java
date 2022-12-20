package br.com.juwer.algafoodapi.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

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
  public Restaurante buscar(@PathVariable Long restauranteId) {
    return restauranteService.buscaOuFalha(restauranteId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Restaurante adicionar(@RequestBody Restaurante restaurante) {
      return restauranteService.salvar(restaurante);
  }


  @PutMapping("/{restauranteId}")
  public Restaurante atualizar(@PathVariable Long restauranteId,
                       @RequestBody Restaurante restaurante) {
      
    Restaurante restauranteAtual = restauranteService.buscaOuFalha(restauranteId);
    BeanUtils.copyProperties(restaurante, restauranteAtual,
        "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

    return restauranteService.salvar(restauranteAtual);
  }


  @DeleteMapping("/{restauranteId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long restauranteId) {
      restauranteService.excluir(restauranteId);
  }

  @PatchMapping("/{restauranteId}")
  public Restaurante atualizarParcial(@PathVariable Long restauranteId,
    @RequestBody Map<String, Object> campos) {
    
    Restaurante restauranteAtual = restauranteService.buscaOuFalha(restauranteId);
    
    merge(campos, restauranteAtual);

    return atualizar(restauranteId, restauranteAtual);
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