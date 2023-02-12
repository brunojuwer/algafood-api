package br.com.juwer.algafoodapi.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class VendaDiaria {
    private LocalDate data;
    private Long totalVendas;
    private BigDecimal totalFaturado;
}
