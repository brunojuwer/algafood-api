package br.com.juwer.algafoodapi.domain.repository;

import br.com.juwer.algafoodapi.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {}
