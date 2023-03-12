package br.com.juwer.algafoodapi.api.v1.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.v1.model.dto.FormaPagamentoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.formapagamentodtos.FormaPagamentoDTOInput;
import br.com.juwer.algafoodapi.api.v1.openapi.model.FormasPagamentoModelOpenApi;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@Api(tags = "Formas Pagamento")
public interface FormaPagamentoControllerOpenApi {

    @ApiOperation(value = "Listar todas as formas de pagamento")
    @ApiResponse(code = 200, message = "OK", response = FormasPagamentoModelOpenApi.class)
    ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request);

    @ApiOperation(value = "Buscar forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Id da forma de pagamento inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = Problem.class)
    })
    ResponseEntity<FormaPagamentoDTO> buscar(@ApiParam(value = "Id da forma de pagamento") Long formaPagamentoId);

    @ApiOperation(value = "Adiciona forma de pagamento")
    FormaPagamentoDTO adicionar(FormaPagamentoDTOInput formaPagamentoDTOInput);

    @ApiOperation(value = "Atualiza a forma de pagamento")
    FormaPagamentoDTO atualizar(@ApiParam(value = "ID da forma de pagamento") Long formaPagamentoID,
                                FormaPagamentoDTOInput formaPagamentoDTOInput);

    @ApiOperation(value = "Excluir forma de pagamento por ID")
    void excluir(@ApiParam(value = "ID da forma de pagamento") Long formaPagamentoID);
}