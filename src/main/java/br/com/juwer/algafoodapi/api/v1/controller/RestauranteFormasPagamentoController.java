package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.FormaPagamentoDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.FormaPagamentoDTO;
import br.com.juwer.algafoodapi.api.v1.openapi.controller.RestauranteFormasPagamentoControllerOpenApi;
import br.com.juwer.algafoodapi.api.v1.HateoasAlgaLinks;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormasPagamentoController implements RestauranteFormasPagamentoControllerOpenApi {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;

    @Autowired
    private HateoasAlgaLinks hateoasAlgaLinks;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<FormaPagamentoDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscaOuFalha(restauranteId);

        CollectionModel<FormaPagamentoDTO> formasPagamentoDTO =
                formaPagamentoDTOAssembler.toCollectionModel(restaurante.getFormasPagamento()).removeLinks()
                        .add(hateoasAlgaLinks.linkToSelfFormasPagamentorestaurante(restauranteId))
                        .add(hateoasAlgaLinks.linkToFormasPagamentoRestauranteAssociar(restauranteId, "associar"));

        formasPagamentoDTO.getContent().forEach(formaPagamento -> {
            formaPagamento.add(hateoasAlgaLinks.linkToFormasPagamentoRestauranteDesassociar(
                    restauranteId, formaPagamento.getId(), "desassociar"));
        });

        return formasPagamentoDTO;
    }

    @Override
    @PutMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestauranteService.associarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
        cadastroRestauranteService.desassociarFormaPagamento(restauranteId, formaPagamentoId);
        return ResponseEntity.noContent().build();
    }

}