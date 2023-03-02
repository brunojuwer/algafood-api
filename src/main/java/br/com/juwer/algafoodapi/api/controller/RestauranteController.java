package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.RestauranteDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.RestauranteDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.RestauranteDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.restaurantedtos.RestauranteDTOInput;
import br.com.juwer.algafoodapi.api.model.dto.view.RestauranteView;
import br.com.juwer.algafoodapi.api.openapi.controller.RestauranteControllerOpenApi;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController implements RestauranteControllerOpenApi {

  @Autowired
  private RestauranteRepository restauranteRepository;
  
  @Autowired
  private CadastroRestauranteService restauranteService;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private RestauranteDTOAssembler restauranteDTOAssembler;

  @Autowired
  private RestauranteDTODisassembler restauranteDTODisassembler;

  @Override
  @JsonView(RestauranteView.Resumo.class)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<RestauranteDTO>> listar(){
    List<RestauranteDTO> restauranteDTOS = restauranteDTOAssembler
            .toCollectionModel(restauranteService.buscarResumo());

    return ResponseEntity
            .ok()
            .body(restauranteDTOS);
  }

  @Override
  @JsonView(RestauranteView.ApenasNomeEId.class)
  @GetMapping(params = "projecao=apenas-nome", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<RestauranteDTO>> listarApenasNomes(){
    return this.listar();
  }

  @Override
  @GetMapping(path = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public RestauranteDTO buscar(@PathVariable Long restauranteId) {
    Restaurante restaurante = restauranteService.buscaOuFalha(restauranteId);
    return restauranteDTOAssembler.toModel(restaurante);
  }

  @Override
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public RestauranteDTO adicionar(@RequestBody @Valid RestauranteDTOInput restauranteDTOInput) {

    try {
      Restaurante restaurante = restauranteDTODisassembler.toDomainObject(restauranteDTOInput);
      return restauranteDTOAssembler.toModel(restauranteService.salvar(restaurante));
    } catch (EntidadeNaoEncontradaException e) {
        throw new NegocioException(e.getMessage());
    }
  }

  @Override
  @PutMapping(path = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public RestauranteDTO atualizar(@PathVariable Long restauranteId,
                                  @RequestBody @Valid RestauranteDTOInput restauranteDTOInput) {
      
    Restaurante restauranteAtual = restauranteService.buscaOuFalha(restauranteId);
    restauranteDTODisassembler.copyToDomainObject(restauranteDTOInput, restauranteAtual);

    try {
      return restauranteDTOAssembler.toModel(restauranteService.salvar(restauranteAtual));
    } catch (EntidadeNaoEncontradaException e) {
        throw new NegocioException(e.getMessage());
    }
  }

  @Override
  @DeleteMapping("/{restauranteId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long restauranteId) {
      restauranteService.excluir(restauranteId);
  }

  @Override
  @PutMapping("/{restauranteId}/ativo")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void ativar(@PathVariable Long restauranteId) {
    restauranteService.ativar(restauranteId);
  }

  @Override
  @DeleteMapping("/{restauranteId}/ativo")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void inativar(@PathVariable Long restauranteId) {
    restauranteService.inativar(restauranteId);
  }

  @Override
  @PutMapping("/ativacoes")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
    try {
      restauranteService.ativar(restauranteIds);
    } catch (EntidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @Override
  @DeleteMapping("/ativacoes")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
    try {
      restauranteService.inativar(restauranteIds);
    } catch (EntidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @Override
  @PutMapping("/{restauranteId}/abertura")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void abrir(@PathVariable Long restauranteId) {
      restauranteService.abrir(restauranteId);
  }

  @Override
  @PutMapping("/{restauranteId}/fechamento")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void fechar(@PathVariable Long restauranteId) {
      restauranteService.fechar(restauranteId);
  }
}