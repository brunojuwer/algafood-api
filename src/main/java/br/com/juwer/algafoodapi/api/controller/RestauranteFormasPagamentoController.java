package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.FormaPagamentoDTOAssembler;
import br.com.juwer.algafoodapi.api.model.dto.FormaPagamentoDTO;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormasPagamentoController {
  
  @Autowired
  private CadastroRestauranteService cadastroRestauranteService;

  @Autowired
  private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

  @GetMapping
  public List<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
    Restaurante restaurante = cadastroRestauranteService.buscaOuFalha(restauranteId);

    return formaPagamentoDTOAssembler.toCollectionModel(restaurante.getFormasPagamento());
  }

  @PutMapping("/{formaPagamentoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
      cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
  }

  @DeleteMapping("/{formaPagamentoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
    cadastroRestauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
  }

}