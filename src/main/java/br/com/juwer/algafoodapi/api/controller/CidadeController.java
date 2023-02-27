package br.com.juwer.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.juwer.algafoodapi.api.assembler.CidadeDTOAssembler;
import br.com.juwer.algafoodapi.api.disassembler.CidadeDTODisassembler;
import br.com.juwer.algafoodapi.api.model.dto.CidadeDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.CidadeDTOInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "Cidades")
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

  @ApiOperation(value = "Listar as cidades")
  @GetMapping
  public List<CidadeDTO> listar(){
    return cidadeDTOAssembler.toCollectionModel(cidadeRepository.findAllCidades());
  }

  @ApiOperation(value = "Buscar uma cidade por ID")
  @GetMapping("/{cidadeId}")
  private CidadeDTO buscar(@PathVariable Long cidadeId) {
    return cidadeDTOAssembler.toModel(cadastroCidadeService.buscaOuFalha(cidadeId));
  }

  @ApiOperation(value = "Cadastrar uma cidade")
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

  @ApiOperation(value = "Atualizar uma cidade por ID")
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

  @ApiOperation(value = "Deletar uma cidade por ID")
  @DeleteMapping("/{cidadeId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long cidadeId) {
    cadastroCidadeService.excluir(cidadeId);
  }
}
