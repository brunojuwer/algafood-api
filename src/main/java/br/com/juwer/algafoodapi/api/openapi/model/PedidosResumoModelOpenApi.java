package br.com.juwer.algafoodapi.api.openapi.model;

import br.com.juwer.algafoodapi.api.model.dto.CozinhaDTO;
import br.com.juwer.algafoodapi.api.model.dto.PedidoResumoDTO;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "CozinhasModel")
public class PedidosResumoModelOpenApi extends PagedModelOpenApi<PedidoResumoDTO> {}