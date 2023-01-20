package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.RestauranteNaoEncontradoException;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.model.FormaPagamento;
import br.com.juwer.algafoodapi.domain.model.Restaurante;
import br.com.juwer.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {
  
  private static final String MSG_RESTAURANTE_EM_USO =
          "Restaurante de código %d não pode ser removido, pois está em uso";

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinhaService;

  @Autowired
  private CadastroCidadeService cadastroCidadeService;

  @Autowired
  private CadastroFormaPagamentoService cadastroFormaPagamentoService;

  @Transactional
  public Restaurante salvar(Restaurante restaurante) {
    
    Long cozinhaId = restaurante.getCozinha().getId();
    Cozinha cozinha = cadastroCozinhaService.buscaOuFalha(cozinhaId);
    Long cidadeId = restaurante.getEndereco().getCidade().getId();
    Cidade cidade = cadastroCidadeService.buscaOuFalha(cidadeId);

    restaurante.setCozinha(cozinha);
    restaurante.getEndereco().setCidade(cidade);

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

  @Transactional
  public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
    Restaurante restaurante = buscaOuFalha(restauranteId);
    FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

    restaurante.getFormasPagamento().add(formaPagamento);
  }

  @Transactional
  public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
    Restaurante restaurante = buscaOuFalha(restauranteId);
    FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

    restaurante.getFormasPagamento().remove(formaPagamento);
  }
  
  public Restaurante buscaOuFalha(Long restauranteId) {
    return restauranteRepository.findById(restauranteId)
             .orElseThrow(() -> 
               new RestauranteNaoEncontradoException(restauranteId));
  }
}
