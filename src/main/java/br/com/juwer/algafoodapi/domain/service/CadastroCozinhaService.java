package br.com.juwer.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
   
  private final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código: %d";
  private final String MSG_CIDADE_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso.";
  
  @Autowired
  private CozinhaRepository cozinhaRepository;

  public Cozinha salvar(Cozinha cozinha) {
    return cozinhaRepository.save(cozinha);
  }

  public void excluir(Long cozinhaId) {

    try {
      cozinhaRepository.deleteById(cozinhaId);      
    } catch(EmptyResultDataAccessException e){
        throw new EntidadeNaoEncontradaException(
          String.format(MSG_CIDADE_NAO_ENCONTRADA, cozinhaId)
        );
    } catch (DataIntegrityViolationException e) {
        throw new EntidadeEmUsoException(
          String.format(MSG_CIDADE_EM_USO, cozinhaId));
    }
  }
  
  
  public Cozinha buscaOuFalha(Long cozinhaId) {
    return cozinhaRepository.findById(cozinhaId)
        .orElseThrow(() -> new EntidadeNaoEncontradaException(
            String.format(MSG_CIDADE_NAO_ENCONTRADA, cozinhaId)
         ));
  }
}
