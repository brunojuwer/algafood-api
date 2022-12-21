package br.com.juwer.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EstadoNaoEncontradoException;
import br.com.juwer.algafoodapi.domain.model.Estado;
import br.com.juwer.algafoodapi.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
  
  private final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso.";
  
  @Autowired
  private EstadoRepository estadoRepository;

  public Estado salvar(Estado estado) {
    return estadoRepository.save(estado);
  }

  public void excluir(Long estadoId) {
    
    try {
      estadoRepository.deleteById(estadoId);      
    } catch (EmptyResultDataAccessException e) {
        throw new EstadoNaoEncontradoException(estadoId);
    } catch (DataIntegrityViolationException e) {
        throw new EntidadeEmUsoException(
         String.format(MSG_ESTADO_EM_USO, estadoId)
        );
    }   
  }
  
  public Estado buscaOuFalha(Long estadoId) {
    return estadoRepository.findById(estadoId)
             .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
  }
}
