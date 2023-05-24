package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.input.restaurantedtos.RestauranteDTOInput;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteBasicoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.projections.RestauranteResumoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@SecurityRequirement(name = "security_auth")
public interface RestauranteControllerSpringDoc {
    CollectionModel<RestauranteBasicoDTO> listar();

    CollectionModel<RestauranteResumoDTO> listarApenasNomes();

    RestauranteDTO buscar(Long restauranteId);

    RestauranteDTO adicionar(RestauranteDTOInput restauranteDTOInput);

    RestauranteDTO atualizar(Long restauranteId,
                             RestauranteDTOInput restauranteDTOInput);

    void excluir(Long restauranteId);

    ResponseEntity<Void> ativar(Long restauranteId);

    ResponseEntity<Void> inativar(Long restauranteId);

    void ativarMultiplos(List<Long> restauranteIds);

    void inativarMultiplos(List<Long> restauranteIds);

    ResponseEntity<Void> abrir(Long restauranteId);

    ResponseEntity<Void> fechar(Long restauranteId);
}
