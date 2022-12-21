package br.com.juwer.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
  
  private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removido, pois está em uso";
  private static final String MSG_RESTAURANTE_NAO_ECONTRADO = "Não existe um cadastro de restaurante com código %d";

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinhaService;


  public Restaurante salvar(Restaurante restaurante) {
    
    Long cozinhaId = restaurante.getCozinha().getId();
    Cozinha cozinha = cadastroCozinhaService.buscaOuFalha(cozinhaId);
    restaurante.setCozinha(cozinha);

    return restauranteRepository.save(restaurante);
  }

  public void excluir(Long restauranteId) {
    
    try {
      restauranteRepository.deleteById(restauranteId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(
          String.format(MSG_RESTAURANTE_NAO_ECONTRADO, restauranteId)
        );
  } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format(MSG_RESTAURANTE_EM_USO, restauranteId)
        );
    }
  }
  
  public Restaurante buscaOuFalha(Long restauranteId) {
    return restauranteRepository.findById(restauranteId)
             .orElseThrow(() -> new EntidadeNaoEncontradaException(
                 String.format(MSG_RESTAURANTE_NAO_ECONTRADO, restauranteId)));
  }
}
