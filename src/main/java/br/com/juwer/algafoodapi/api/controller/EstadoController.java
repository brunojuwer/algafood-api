package br.com.juwer.algafoodapi.api.controller;

import java.util.List;

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
  public Estado buscar(@PathVariable Long estadoId) {
    return cadastroEstadoService.buscaOuFalha(estadoId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Estado adicionar(@RequestBody Estado estado) {
    return cadastroEstadoService.salvar(estado);
  }

  @PutMapping("/{estadoId}")
  public Estado atualizar(@PathVariable Long estadoId, 
          @RequestBody Estado estado) {
    Estado estadoAtual = cadastroEstadoService.buscaOuFalha(estadoId);
    BeanUtils.copyProperties(estado, estadoAtual, "id");
    return cadastroEstadoService.salvar(estadoAtual);
  }

  @DeleteMapping("/{estadoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long estadoId) {
      cadastroEstadoService.excluir(estadoId);
  }
}
