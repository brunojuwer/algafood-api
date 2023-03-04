package br.com.juwer.algafoodapi.api.controller;

import br.com.juwer.algafoodapi.api.assembler.EstadoDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.EstadoDTODIsassembler;
import br.com.juwer.algafoodapi.api.model.dto.EstadoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.EstadoDTOInput;
import br.com.juwer.algafoodapi.api.openapi.controller.EstadoControllerOpenApi;
import br.com.juwer.algafoodapi.api.utils.ResourceUriHelper;
import br.com.juwer.algafoodapi.domain.model.Estado;
import br.com.juwer.algafoodapi.domain.repository.EstadoRepository;
import br.com.juwer.algafoodapi.domain.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController implements EstadoControllerOpenApi {
  
  @Autowired
  private EstadoRepository estadoRepository;

  @Autowired
  private CadastroEstadoService cadastroEstadoService;

  @Autowired
  private EstadoDTOAssembler estadoDTOAssembler;

  @Autowired
  private EstadoDTODIsassembler estadoDTODIsassembler;

  @Override
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<EstadoDTO> listar() {
    List<Estado> estados = estadoRepository.findAll();
    return estadoDTOAssembler.toCollectionModel(estados);
  }

  @Override
  @GetMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public EstadoDTO buscar(@PathVariable Long estadoId) {
    Estado estado = cadastroEstadoService.buscaOuFalha(estadoId);
    return estadoDTOAssembler.toModel(estado);
  }

  @Override
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public EstadoDTO adicionar(@RequestBody @Valid EstadoDTOInput estadoDTOInput) {
    Estado estado = estadoDTODIsassembler.toDomainObject(estadoDTOInput);
    EstadoDTO estadoDTO = estadoDTOAssembler.toModel(cadastroEstadoService.salvar(estado));

    ResourceUriHelper.addUriResponseHeader(estadoDTO.getId());

    return estadoDTO;
  }

  @Override
  @PutMapping(path = "/{estadoId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public EstadoDTO atualizar(@PathVariable Long estadoId,
                             @RequestBody @Valid EstadoDTOInput estadoDTOInput) {
    Estado estadoAtual = cadastroEstadoService.buscaOuFalha(estadoId);
    estadoDTODIsassembler.copyToDomainObject(estadoDTOInput, estadoAtual);

    return estadoDTOAssembler.toModel(cadastroEstadoService.salvar(estadoAtual));
  }

  @Override
  @DeleteMapping(value = "/{estadoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluir(@PathVariable Long estadoId) {
      cadastroEstadoService.excluir(estadoId);
  }
}
