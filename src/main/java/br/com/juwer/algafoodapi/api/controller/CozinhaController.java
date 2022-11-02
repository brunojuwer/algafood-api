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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.model.Cozinha;
import br.com.juwer.algafoodapi.domain.repository.CozinhaRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
  
  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private CadastroCozinhaService cozinhaService;


  @GetMapping()
  public List<Cozinha> listar(){
    return cozinhaRepository.listar();
  }

  @GetMapping("/{cozinhaId}")
  public ResponseEntity<Cozinha>  buscar(@PathVariable Long cozinhaId){
    Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

    if (cozinha != null){
      return ResponseEntity.ok().body(cozinha);
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cozinha adicionar(@RequestBody Cozinha cozinha){
    return cozinhaService.salvar(cozinha);
  }

  @PutMapping("/{cozinhaId}")
  public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
          @RequestBody Cozinha cozinha) {
    Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);

    
		if (cozinhaAtual != null) {
      //			cozinhaAtual.setNome(cozinha.getNome());
      BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            
      cozinhaAtual = cozinhaService.salvar(cozinhaAtual);
      return ResponseEntity.ok(cozinhaAtual);
    }
          
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{cozinhaId}")
  public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {

    try {
        cozinhaService.excluir(cozinhaId);
        return ResponseEntity.noContent().build();

      } catch (EntidadeEmUsoException e) {
          return ResponseEntity.status(HttpStatus.CONFLICT).build();

      } catch (EntidadeNaoEncontradaException e) {
          return ResponseEntity.notFound().build();
      }
  }
}    
