package br.com.juwer.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.juwer.algafoodapi.api.assembler.EstadoDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.EstadoDTODIsassembler;
import br.com.juwer.algafoodapi.api.model.dto.EstadoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.EstadoDTOInput;
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

  @Autowired
  private EstadoDTOAssembler estadoDTOAssembler;

  @Autowired
  private EstadoDTODIsassembler estadoDTODIsassembler;

  @GetMapping
  public List<EstadoDTO> listar() {
    List<Estado> estados = estadoRepository.findAll();
    return estadoDTOAssembler.convertToDTOList(estados);
  }

  @GetMapping("/{estadoId}")
  public EstadoDTO buscar(@PathVariable Long estadoId) {
    Estado estado = cadastroEstadoService.buscaOuFalha(estadoId);
    return estadoDTOAssembler.convertToEstadoDTO(estado);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EstadoDTO adicionar(@RequestBody @Valid EstadoDTOInput estadoDTOInput) {
    Estado estado = estadoDTODIsassembler.convertToDomainModel(estadoDTOInput);
    Estado estadoSalvo = cadastroEstadoService.salvar(estado);
    return estadoDTOAssembler.convertToEstadoDTO(estadoSalvo);
  }

  @PutMapping("/{estadoId}")
  public EstadoDTO atualizar(@PathVariable Long estadoId,
          @RequestBody @Valid EstadoDTOInput estadoDTOInput) {
    Estado estadoAtual = cadastroEstadoService.buscaOuFalha(estadoId);
    estadoDTODIsassembler.copyToDomainModel(estadoDTOInput, estadoAtual);

    return estadoDTOAssembler.convertToEstadoDTO(cadastroEstadoService.salvar(estadoAtual));
  }

  @DeleteMapping("/{estadoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long estadoId) {
      cadastroEstadoService.excluir(estadoId);
  }
}
