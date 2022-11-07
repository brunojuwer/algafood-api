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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.model.Estado;
import br.com.juwer.algafoodapi.domain.repository.EstadoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {
  
  @Autowired
  private EstadoRepository estadoRepository;

  @Autowired
  private CadastroEstadoService cadastroEstadoService;

  @GetMapping
  public List<Estado> listar() {
    return estadoRepository.findAll();
  }

  @GetMapping("/{estadoId}")
  public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
    
    Optional<Estado> estado = estadoRepository.findById(estadoId);
    

    if(estado.isPresent()) {
      return ResponseEntity.ok(estado.get());
    }
    
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Estado adicionar(@RequestBody Estado estado) {
    return cadastroEstadoService.salvar(estado);
  }

  @PutMapping("/{estadoId}")
  public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, 
  @RequestBody Estado estado) {

    Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);

    if (estadoAtual.isPresent()) {
      BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
        Estado estadoSalvo = cadastroEstadoService.salvar(estadoAtual.get());
          return ResponseEntity.ok(estadoSalvo);
    }

    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{estadoId}")
  public ResponseEntity<?> excluir(@PathVariable Long estadoId) {
    
    try {
      cadastroEstadoService.excluir(estadoId);
      return ResponseEntity.noContent().build();
    } catch (EntidadeEmUsoException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    } catch (EntidadeNaoEncontradaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
