package br.com.juwer.algafoodapi.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
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

import br.com.juwer.algafoodapi.core.validation.ValidacaoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
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

  @Autowired
  private ObjectMapper objectMapper;
  
  @Autowired
  private SmartValidator smartValidator;

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
  public Restaurante adicionar(
    @RequestBody @Valid 
    Restaurante restaurante) {

    try {
      return restauranteService.salvar(restaurante);
    } catch (EntidadeNaoEncontradaException e) {
        throw new NegocioException(e.getMessage());
    }
  }


  @PutMapping("/{restauranteId}")
  public Restaurante atualizar(@PathVariable Long restauranteId,
                       @RequestBody @Valid Restaurante restaurante) {
      
    Restaurante restauranteAtual = restauranteService.buscaOuFalha(restauranteId);
    BeanUtils.copyProperties(restaurante, restauranteAtual,
        "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
    try {
      return restauranteService.salvar(restauranteAtual);
    } catch (EntidadeNaoEncontradaException e) {
        throw new NegocioException(e.getMessage());
    }
  }
  
  @PatchMapping("/{restauranteId}")
  public Restaurante atualizarParcial(@PathVariable Long restauranteId,
  @RequestBody Map<String, Object> campos, HttpServletRequest request) {
    
    Restaurante restauranteAtual = restauranteService.buscaOuFalha(restauranteId);
    
    merge(campos, restauranteAtual, request);
    validate(restauranteAtual, "restaurante");

    return atualizar(restauranteId, restauranteAtual);
  }

  private void validate(Restaurante restaurante, String objectName) {
    var bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
    smartValidator.validate(restaurante, bindingResult);
    
    if(bindingResult.hasErrors()) {
      throw new ValidacaoException(bindingResult);
    }
  }
  
  @SuppressWarnings("null")
  private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino,
  HttpServletRequest request) {
    ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
    
    try {
      Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
      
      camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
        
        Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
        field.setAccessible(true);
        
        Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
        
        ReflectionUtils.setField(field, restauranteDestino, novoValor);
      });
      
    } catch (IllegalArgumentException ex) {
      Throwable rootCause = ExceptionUtils.getRootCause(ex);
      
      throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, serverHttpRequest);
    }
  }

  @DeleteMapping("/{restauranteId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long restauranteId) {
      restauranteService.excluir(restauranteId);
  }
}