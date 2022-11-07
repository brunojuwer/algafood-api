package br.com.juwer.algafoodapi.api.controller;

import java.util.List;
import java.util.Optional;

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
    return cidadeRepository.findAll();
  }
  
  @GetMapping("/{cidadeId}")
  private ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
    Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

    if(cidade.isPresent()) {
      return ResponseEntity.ok(cidade.get());
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
      
    Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);
        
      try {
        
        if(cidadeAtual.isPresent()) {

          BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
          Cidade cidadeSalva = cadastroCidadeService.salvar(cidadeAtual.get());
          return ResponseEntity.ok(cidadeSalva);
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
