package br.com.juwer.algafoodapi.domain.repository;

import br.com.juwer.algafoodapi.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
