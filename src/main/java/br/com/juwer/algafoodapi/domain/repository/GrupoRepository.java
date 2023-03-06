package br.com.juwer.algafoodapi.domain.repository;

import br.com.juwer.algafoodapi.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    @Query(value = "SELECT * FROM grupo g " +
            "JOIN usuario_grupo ug ON ug.grupo_id  = g.id " +
            "JOIN usuario u ON ug.usuario_id  = u.id where u.id = :usuarioId", nativeQuery = true)
    List<Grupo> findGruposByUsusarioId(Long usuarioId);
}
