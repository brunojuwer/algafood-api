package br.com.juwer.algafoodapi.api.openapi.controller;

import br.com.juwer.algafoodapi.api.exceptionhandler.Problem;
import br.com.juwer.algafoodapi.api.model.dto.FormaPagamentoDTO;
import br.com.juwer.algafoodapi.api.model.dto.input.formapagamentodtos.FormaPagamentoDTOInput;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Formas Pagamento")
public interface FormaPagamentoControllerOpenApi {

    @ApiOperation(value = "Listar todas as formas de pagamento")
    ResponseEntity<List<FormaPagamentoDTO>> listar(ServletWebRequest request);

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