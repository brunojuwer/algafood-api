package br.com.juwer.algafoodapi.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import br.com.juwer.algafoodapi.api.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.model.dto.RestauranteDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.RestauranteDTOIn;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
  public List<RestauranteDTO> listar(){
    return convertToDtoList(restauranteRepository.findAll());
  }

  @GetMapping("/{restauranteId}")
  public RestauranteDTO buscar(@PathVariable Long restauranteId) {
    Restaurante restaurante = restauranteService.buscaOuFalha(restauranteId);
    return convertToDTO(restaurante);
  }



  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestauranteDTO adicionar(@RequestBody @Valid RestauranteDTOIn restauranteDTOIn) {

    try {
      Restaurante restaurante = convertDTOInToRestaurante(restauranteDTOIn);
      return convertToDTO(restauranteService.salvar(restaurante));
    } catch (EntidadeNaoEncontradaException e) {
        throw new NegocioException(e.getMessage());
    }
  }


  @PutMapping("/{restauranteId}")
  public RestauranteDTO atualizar(@PathVariable Long restauranteId,
                       @RequestBody @Valid RestauranteDTOIn restauranteDTOIn) {
      
    Restaurante restauranteAtual = restauranteService.buscaOuFalha(restauranteId);
    Restaurante restaurante = convertDTOInToRestaurante(restauranteDTOIn);
    BeanUtils.copyProperties(restaurante, restauranteAtual,
        "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
    try {
      return convertToDTO(restauranteService.salvar(restauranteAtual));
    } catch (EntidadeNaoEncontradaException e) {
        throw new NegocioException(e.getMessage());
    }
  }
  
//  @PatchMapping("/{restauranteId}")
//  public RestauranteDTO atualizarParcial(@PathVariable Long restauranteId,
//  @RequestBody Map<String, Object> campos, HttpServletRequest request) {
//
//    Restaurante restauranteAtual = restauranteService.buscaOuFalha(restauranteId);
//
//    merge(campos, restauranteAtual, request);
//    validate(restauranteAtual, "restaurante");
//
//    return atualizar(restauranteId, restauranteAtual);
//  }
//
//  private void validate(Restaurante restaurante, String objectName) {
//    var bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
//    smartValidator.validate(restaurante, bindingResult);
//
//    if(bindingResult.hasErrors()) {
//      throw new ValidacaoException(bindingResult);
//    }
//  }
//
//  @SuppressWarnings("null")
//  private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino,
//  HttpServletRequest request) {
//    ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
//
//    try {
//      Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
//
//      camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
//
//        Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
//        field.setAccessible(true);
//
//        Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//
//        ReflectionUtils.setField(field, restauranteDestino, novoValor);
//      });
//
//    } catch (IllegalArgumentException ex) {
//      Throwable rootCause = ExceptionUtils.getRootCause(ex);
//
//      throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, serverHttpRequest);
//    }
//  }

  @DeleteMapping("/{restauranteId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long restauranteId) {
      restauranteService.excluir(restauranteId);
  }

  private RestauranteDTO convertToDTO(Restaurante restaurante) {
    RestauranteDTO restauranteDTO = new RestauranteDTO();

    CozinhaDTO cozinhaDTO = new CozinhaDTO();
    cozinhaDTO.setId(restaurante.getCozinha().getId());
    cozinhaDTO.setNome(restaurante.getCozinha().getNome());

    restauranteDTO.setId(restaurante.getId());
    restauranteDTO.setNome(restaurante.getNome());
    restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
    restauranteDTO.setCozinhaDTO(cozinhaDTO);
    return restauranteDTO;
  }

  private List<RestauranteDTO> convertToDtoList(List<Restaurante> restaurantes) {
    return restaurantes.stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  private Restaurante convertDTOInToRestaurante(RestauranteDTOIn restauranteDTOIn) {
    Restaurante restaurante = new Restaurante();
    Cozinha cozinha = new Cozinha();
    cozinha.setId(restauranteDTOIn.getCozinha().getId());

    restaurante.setNome(restauranteDTOIn.getNome());
    restaurante.setTaxaFrete(restauranteDTOIn.getTaxaFrete());
    restaurante.setCozinha(cozinha);

    return restaurante;
  }
}