package br.com.juwer.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {
  
  private static final String MSG_RESTAURANTE_EM_USO =
          "Restaurante de código %d não pode ser removido, pois está em uso";

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinhaService;

  @Transactional
  public Restaurante salvar(Restaurante restaurante) {
    
    Long cozinhaId = restaurante.getCozinha().getId();
    Cozinha cozinha = cadastroCozinhaService.buscaOuFalha(cozinhaId);
    restaurante.setCozinha(cozinha);

    return restauranteRepository.save(restaurante);
  }

  @Transactional
  public void excluir(Long restauranteId) {
    
    try {
      restauranteRepository.deleteById(restauranteId);
      restauranteRepository.flush();
    } catch (EmptyResultDataAccessException e) {
      throw new RestauranteNaoEncontradoException(restauranteId);
  } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format(MSG_RESTAURANTE_EM_USO, restauranteId)
        );
    }
  }

  @Transactional
  public void ativar(Long restauranteId){
    Restaurante restaurante = buscaOuFalha(restauranteId);
    restaurante.ativar();
  }

  @Transactional
  public void inativar(Long restauranteId){
    Restaurante restaurante = buscaOuFalha(restauranteId);
    restaurante.inativar();
  }
  
  public Restaurante buscaOuFalha(Long restauranteId) {
    return restauranteRepository.findById(restauranteId)
             .orElseThrow(() -> 
               new RestauranteNaoEncontradoException(restauranteId));
  }
}
