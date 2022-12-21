package br.com.juwer.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.juwer.algafoodapi.domain.exception.CidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.model.Estado;
import br.com.juwer.algafoodapi.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
  
  private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";
  
  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private CadastroEstadoService cadastroEstadoService;


  public Cidade salvar(Cidade cidade) {
    Long estadoId = cidade.getEstado().getId();
    Estado estado = cadastroEstadoService.buscaOuFalha(estadoId);
    cidade.setEstado(estado);
    
    return cidadeRepository.save(cidade);
  }

  public void excluir(Long cidadeId) {

    try {
      cidadeRepository.deleteById(cidadeId);
    } catch (EmptyResultDataAccessException e) {
        throw new CidadeNaoEncontradaException(cidadeId);
    } catch(DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
        String.format(MSG_CIDADE_EM_USO, cidadeId));
    }
  }
  
  public Cidade buscaOuFalha(Long cidadeId) {
    return cidadeRepository.findById(cidadeId)
            .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
  }
}
