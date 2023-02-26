package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.RestauranteDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.RestauranteDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.RestauranteDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.restaurantedtos.RestauranteDTOInput;
import br.com.juwer.algafoodapi.api.model.dto.view.RestauranteView;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
  private RestauranteDTOAssembler restauranteDTOAssembler;

  @Autowired
  private RestauranteDTODisassembler restauranteDTODisassembler;

  @JsonView(RestauranteView.Resumo.class)

  @GetMapping
  public ResponseEntity<List<RestauranteDTO>> listar(){
    List<RestauranteDTO> restauranteDTOS = restauranteDTOAssembler
            .toCollectionModel(restauranteService.buscarResumo());

    return ResponseEntity
            .ok()
            .body(restauranteDTOS);
  }

//
//  @GetMapping(params = "projecao=resumo")
//  public List<RestauranteDTO> listarResumo(){
//    return this.listar();
//  }

//  @GetMapping
//  public MappingJacksonValue listar(@RequestParam(required = false) String projecao){
//      List<Restaurante> restaurantes = restauranteRepository.findAll();
//      List<RestauranteDTO> restaurantesDTO = restauranteDTOAssembler.toCollectionModel(restaurantes);
//
//      MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesDTO);
//
//      if("apenas-nome-e-id".equals(projecao)){
//        restaurantesWrapper.setSerializationView(RestauranteView.ApenasNomeEId.class);
//          return restaurantesWrapper;
//      }
//
//      restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//      return restaurantesWrapper;
//  }

  @GetMapping("/{restauranteId}")
  public RestauranteDTO buscar(@PathVariable Long restauranteId) {
    Restaurante restaurante = restauranteService.buscaOuFalha(restauranteId);
    return restauranteDTOAssembler.toModel(restaurante);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestauranteDTO adicionar(@RequestBody @Valid RestauranteDTOInput restauranteDTOInput) {

    try {
      Restaurante restaurante = restauranteDTODisassembler.toDomainObject(restauranteDTOInput);
      return restauranteDTOAssembler.toModel(restauranteService.salvar(restaurante));
    } catch (EntidadeNaoEncontradaException e) {
        throw new NegocioException(e.getMessage());
    }
  }

  @PutMapping("/{restauranteId}")
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

  @DeleteMapping("/{restauranteId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long restauranteId) {
      restauranteService.excluir(restauranteId);
  }

  @PutMapping("/{restauranteId}/ativo")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void ativar(@PathVariable Long restauranteId) {
    restauranteService.ativar(restauranteId);
  }

  @DeleteMapping("/{restauranteId}/ativo")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void inativar(@PathVariable Long restauranteId) {
    restauranteService.inativar(restauranteId);
  }

  @PutMapping("/ativacoes")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
    try {
      restauranteService.ativar(restauranteIds);
    } catch (EntidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @DeleteMapping("/ativacoes")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
    try {
      restauranteService.inativar(restauranteIds);
    } catch (EntidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }
  }

  @PutMapping("/{restauranteId}/abertura")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void abrir(@PathVariable Long restauranteId) {
      restauranteService.abrir(restauranteId);
  }

  @PutMapping("/{restauranteId}/fechamento")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void fechar(@PathVariable Long restauranteId) {
      restauranteService.fechar(restauranteId);
  }
}