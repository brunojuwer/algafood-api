package br.com.juwer.algafoodapi.api.v1.springdoc.controller;

import br.com.juwer.algafoodapi.api.v1.model.dto.FormaPagamentoDTO;
import br.com.juwer.algafoodapi.api.v1.model.dto.input.formapagamentodtos.FormaPagamentoDTOInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@SecurityRequirement(name = "security_auth")
@Tag(name = "FormasPagamento", description = "Gerencia as formas de pagamento")
public interface FormaPagamentoControllerSpringDoc {
    ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request);

    ResponseEntity<FormaPagamentoDTO> buscar(Long formaPagamentoId);

    FormaPagamentoDTO adicionar(FormaPagamentoDTOInput formaPagamentoDTOInput);

    FormaPagamentoDTO atualizar(Long formaPagamentoID,
                                FormaPagamentoDTOInput formaPagamentoDTOInput);

    void excluir(Long formaPagamentoID);
}
