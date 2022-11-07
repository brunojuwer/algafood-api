package br.com.juwer.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.model.Estado;
import br.com.juwer.algafoodapi.domain.repository.CidadeRepository;
import br.com.juwer.algafoodapi.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
  
  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private EstadoRepository estadoRepository;


  public Cidade salvar(Cidade cidade) {
    Long estadoId = cidade.getEstado().getId();
    Estado estado = estadoRepository.buscar(estadoId);

    if (estado == null) {
      throw new EntidadeNaoEncontradaException(
        String.format("Não existe entidade de estado com o código: %d", estadoId)
      );
    }

    cidade.setEstado(estado);
    return cidadeRepository.salvar(cidade);
  }

  public void excluir(Long cidadeId) {

    try {
      cidadeRepository.remover(cidadeId);
    } catch (EmptyResultDataAccessException e) {
        throw new EntidadeNaoEncontradaException(
          String.format("Não existe entidade cidade com o código: %d", cidadeId)
        );
    } catch(DataIntegrityViolationException e) {
        throw new EntidadeEmUsoException(
          String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId)
        );
    }
  }

}