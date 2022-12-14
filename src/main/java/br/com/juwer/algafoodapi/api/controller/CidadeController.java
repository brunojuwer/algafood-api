package br.com.juwer.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;
import br.com.juwer.algafoodapi.domain.model.Cidade;
import br.com.juwer.algafoodapi.domain.repository.CidadeRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
  
  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired 
  private CadastroCidadeService cadastroCidadeService;

  @GetMapping
  public List<Cidade> listar(){
    return cidadeRepository.findAll();
  }
  
  @GetMapping("/{cidadeId}")
  private Cidade buscar(@PathVariable Long cidadeId) {
    return cadastroCidadeService.buscaOuFalha(cidadeId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cidade adicionar(@RequestBody @Valid Cidade cidade ) {
    try {
      return cadastroCidadeService.salvar(cidade);
    } catch(EntidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage());
    }
  }

  @PutMapping("/{cidadeId}")
  public Cidade atualizar(@PathVariable Long cidadeId,
                  @RequestBody @Valid Cidade cidade) {
    Cidade cidadeAtual = cadastroCidadeService.buscaOuFalha(cidadeId);
    BeanUtils.copyProperties(cidade, cidadeAtual, "id");

    try {
      return cadastroCidadeService.salvar(cidadeAtual);
      
    } catch (EntidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage(), e);
    }                  
  }

  @DeleteMapping("/{cidadeId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long cidadeId) {
    cadastroCidadeService.excluir(cidadeId);
  }
}
