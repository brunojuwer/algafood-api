package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.RestauranteDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.RestauranteDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.RestauranteDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.RestauranteDTOInput;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

  @GetMapping
  public List<RestauranteDTO> listar(){
    return restauranteDTOAssembler.convertToDtoList(restauranteRepository.findAll());
  }

  @GetMapping("/{restauranteId}")
  public RestauranteDTO buscar(@PathVariable Long restauranteId) {
    Restaurante restaurante = restauranteService.buscaOuFalha(restauranteId);
    return restauranteDTOAssembler.convertToDTO(restaurante);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RestauranteDTO adicionar(@RequestBody @Valid RestauranteDTOInput restauranteDTOInput) {

    try {
      Restaurante restaurante = restauranteDTODisassembler.convertDTOInputToRestaurante(restauranteDTOInput);
      return restauranteDTOAssembler.convertToDTO(restauranteService.salvar(restaurante));
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
      return restauranteDTOAssembler.convertToDTO(restauranteService.salvar(restauranteAtual));
    } catch (EntidadeNaoEncontradaException e) {
        throw new NegocioException(e.getMessage());
    }
  }

  @DeleteMapping("/{restauranteId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long restauranteId) {
      restauranteService.excluir(restauranteId);
  }
}