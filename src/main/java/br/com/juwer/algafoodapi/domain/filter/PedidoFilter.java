package br.com.juwer.algafoodapi.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Getter
@Setter
public class PedidoFilter {

    @ApiModelProperty(example = "1", value = "ID do cliente para filtro da pesquisa")
    private Long clienteId;

    @ApiModelProperty(example = "1", value = "ID do restaurante para filtro da pesquisa")
    private Long restauranteId;

    @ApiModelProperty(example = "2019-10-30T00:00:00Z",
            value = "Data/hora de criação inicial para filtro da pesquisa")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // evita uma exception de conversão de string para data
    private OffsetDateTime apartirData;

    @ApiModelProperty(example = "2019-11-01T10:00:00Z",
            value = "Data/hora de criação final para filtro da pesquisa")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime ateData;
}
