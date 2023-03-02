package br.com.juwer.algafoodapi.api.openapi.model;

import br.com.juwer.algafoodapi.api.model.dto.CozinhaDTO;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "CozinhasModel")
public class CozinhasModelOpenApi extends PagedModelOpenApi<CozinhaDTO> {}