package br.com.juwer.algafoodapi.domain.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class VendaDiaria {

    @ApiModelProperty(example = "2023-03-02T21:27:03.670Z")
    private Date data;

    @ApiModelProperty(example = "2")
    private Long totalVendas;

    @ApiModelProperty(example = "202,90", dataType = "java.lang.String")
    private BigDecimal totalFaturado;
}
