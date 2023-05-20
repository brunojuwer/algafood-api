package br.com.juwer.algafoodapi.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Setter
@Getter
public class VendaDiariaFilter {

    @ApiModelProperty(example = "1")
    private Long restauranteId;

    @ApiModelProperty(example = "2023-01-02T21:27:03.67Z")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // evita uma exception de conversão de string para data
    private OffsetDateTime apartirData;

    @ApiModelProperty(example = "2023-03-02T22:27:03.67Z")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime ateData;
}
