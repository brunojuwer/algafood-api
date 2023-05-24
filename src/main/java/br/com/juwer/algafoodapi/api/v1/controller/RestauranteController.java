package br.com.juwer.algafoodapi.api.v1.controller;

import br.com.juwer.algafoodapi.api.v1.assembler.RestauranteBasicoDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.assembler.RestauranteDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.assembler.RestauranteResumoDTOAssembler;
import br.com.juwer.algafoodapi.api.v1.disassembler.RestauranteDTODisassembler;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.restaurantedtos.RestauranteDTOInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteBasicoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteResumoDTO;
import br.com.juwer.algafoodapi.api.v1.springdoc.controller.RestauranteControllerSpringDoc;
import br.com.juwer.algafoodapi.core.security.CheckSecurity;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/restaurantes")
public class RestauranteController implements RestauranteControllerSpringDoc {

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

    @Autowired
    private RestauranteResumoDTOAssembler  restauranteResumoDTOAssembler;

    @Autowired
    private RestauranteBasicoDTOAssembler restauranteBasicoDTOAssembler;

    @Override
    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<RestauranteBasicoDTO> listar(){
        return restauranteBasicoDTOAssembler
                .toCollectionModel(restauranteService.buscarResumo());
    }

    @Override
    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(params = "projecao=apenas-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<RestauranteResumoDTO> listarApenasNomes(){
        return restauranteResumoDTOAssembler.toCollectionModel(restauranteService.buscarResumo());
    }

    @Override
    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(path = "/{restauranteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestauranteDTO buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscaOuFalha(restauranteId);
        return restauranteDTOAssembler.toModel(restaurante);
    }

    @Override
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
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
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
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
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long restauranteId) {
        restauranteService.excluir(restauranteId);
    }

    @Override
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> inativar(@PathVariable Long restauranteId) {
        restauranteService.inativar(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
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
    @CheckSecurity.Restaurantes.PodeGerenciarCadastro
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
    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> abrir(@PathVariable Long restauranteId) {
        restauranteService.abrir(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> fechar(@PathVariable Long restauranteId) {
        restauranteService.fechar(restauranteId);
        return ResponseEntity.noContent().build();
    }
}