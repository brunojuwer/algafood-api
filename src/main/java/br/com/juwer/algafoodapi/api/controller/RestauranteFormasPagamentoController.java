package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.FormaPagamentoDTOAssembler;
import br.com.juwer.algafoodapi.api.model.dto.FormaPagamentoDTO;
import br.com.juwer.algafoodapi.api.openapi.controller.RestauranteFormasPagamentoControllerOpenApi;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormasPagamentoController implements RestauranteFormasPagamentoControllerOpenApi {
  
  @Autowired
  private CadastroRestauranteService cadastroRestauranteService;

  @Autowired
  private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

  @Override
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public CollectionModel<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
    Restaurante restaurante = cadastroRestauranteService.buscaOuFalha(restauranteId);

    return formaPagamentoDTOAssembler.toCollectionModel(restaurante.getFormasPagamento()).removeLinks()
            .add(linkTo(methodOn(RestauranteFormasPagamentoController.class).listar(restauranteId)).withSelfRel());
  }

  @Override
  @PutMapping("/{formaPagamentoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
      cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
  }

  @Override
  @DeleteMapping("/{formaPagamentoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
    cadastroRestauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
  }

}