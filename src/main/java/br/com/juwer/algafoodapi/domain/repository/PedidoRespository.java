package br.com.juwer.algafoodapi.domain.repository;

import br.com.juwer.algafoodapi.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRespository extends JpaRepository<Pedido, Long> {}
