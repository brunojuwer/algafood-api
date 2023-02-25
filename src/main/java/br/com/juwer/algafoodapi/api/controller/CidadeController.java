package br.com.juwer.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.juwer.algafoodapi.api.assembler.CidadeDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.CidadeDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.CidadeDTOInput;
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

  @Autowired
  private CidadeDTOAssembler cidadeDTOAssembler;

  @Autowired
  private CidadeDTODisassembler cidadeDTODisassembler;

  @GetMapping
  public List<CidadeDTO> listar(){
    return cidadeDTOAssembler.toCollectionModel(cidadeRepository.findAllCidades());
  }
  
  @GetMapping("/{cidadeId}")
  private CidadeDTO buscar(@PathVariable Long cidadeId) {
    return cidadeDTOAssembler.toModel(cadastroCidadeService.buscaOuFalha(cidadeId));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CidadeDTO adicionar(@RequestBody @Valid CidadeDTOInput cidadeDTOInput) {
    try {
      Cidade cidade = cidadeDTODisassembler.toDomainObject(cidadeDTOInput);
      return cidadeDTOAssembler.toModel(cadastroCidadeService.salvar(cidade));
    } catch(EntidadeNaoEncontradaException e) {
      throw new NegocioException(e.getMessage());
    }
  }

  @PutMapping("/{cidadeId}")
  public CidadeDTO atualizar(@PathVariable Long cidadeId,
                  @RequestBody @Valid CidadeDTOInput cidadeDTOInput) {
    Cidade cidadeAtual = cadastroCidadeService.buscaOuFalha(cidadeId);
    cidadeDTODisassembler.copyToDomainObject(cidadeDTOInput, cidadeAtual);

    try {
      return cidadeDTOAssembler.toModel(cadastroCidadeService.salvar(cidadeAtual));
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
