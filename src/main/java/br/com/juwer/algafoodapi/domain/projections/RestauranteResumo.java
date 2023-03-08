package br.com.juwer.algafoodapi.domain.projections;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class RestauranteResumo {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private Long cozinha_id;
    private String nomeCozinha;
}
