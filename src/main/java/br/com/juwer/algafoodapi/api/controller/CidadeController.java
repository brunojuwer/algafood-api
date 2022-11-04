package br.com.juwer.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
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
    return cidadeRepository.listar();
  }
  
  @GetMapping("/{cidadeId}")
  private ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
    Cidade cidade = cidadeRepository.buscar(cidadeId);

    if(cidade != null) {
      return ResponseEntity.ok(cidade);
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<?> adicionar(@RequestBody Cidade cidade ) {
    try {
      cidade = cadastroCidadeService.salvar(cidade);
      return ResponseEntity.status(HttpStatus.CREATED).body(cidade);

    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{cidadeId}")
  public ResponseEntity<?> atualizar(@PathVariable Long cidadeId,
    @RequestBody Cidade cidade) {
      
    Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);
        
      try {
        
        if(cidadeAtual != null) {

          BeanUtils.copyProperties(cidade, cidadeAtual, "id");
          cidadeAtual = cadastroCidadeService.salvar(cidadeAtual);
          return ResponseEntity.ok(cidadeAtual);

        }        
        return ResponseEntity.notFound().build();
        
      } catch (EntidadeNaoEncontradaException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      } 
  }

  @DeleteMapping("/{cidadeId}")
  public ResponseEntity<?> remover(@PathVariable Long cidadeId) {

    try {
      cadastroCidadeService.excluir(cidadeId);
      return ResponseEntity.noContent().build();
    } catch (EntidadeNaoEncontradaException e) {
       return ResponseEntity.notFound().build();
    } catch (EntidadeEmUsoException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
