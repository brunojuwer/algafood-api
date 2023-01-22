package br.com.juwer.algafoodapi.domain.service;

import br.com.juwer.algafoodapi.domain.exception.PermissaoNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.model.Permissao;
import br.com.juwer.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscaOuFalha(Long permissaoId) {
        return permissaoRepository.findById(permissaoId).orElseThrow(
                () -> new PermissaoNaoEncontradaException(permissaoId)
        );
    }
}
