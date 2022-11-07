package br.com.juwer.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.CozinhaRepository;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
  
  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CozinhaRepository cozinhaRepository;


  public Restaurante salvar(Restaurante restaurante) {
    Long cozinhaId = restaurante.getCozinha().getId();
    Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
      .orElseThrow(() -> 
        new EntidadeNaoEncontradaException(String
        .format("Não existe entidade de cozinha com o código: %d", cozinhaId)));

    restaurante.setCozinha(cozinha);

    return restauranteRepository.salvar(restaurante);
  }

  public void excluir(Long restauranteId) {
    
    try {
      restauranteRepository.remover(restauranteId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(
          String.format("Não existe um cadastro de cidade com código %d", restauranteId)
        );
  } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format("Cidade de código %d não pode ser removida, pois está em uso", restauranteId)
        );
    }
  }
}
