package br.com.juwer.algafoodapi.api.model.dto;

import br.com.juwer.algafoodapi.domain.model.StatusPedido;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
@ApiModel(description = "Retorno completo dos pedidos")
public class PedidoDTO extends RepresentationModel<PedidoDTO> {

    @ApiModelProperty(example = "c5de95d3-7e0d-4afb-9af7-b6022473e079")
    private String codigo;

    @ApiModelProperty(example = "120,00", dataType = "java.lang.String")
    private BigDecimal subTotal;

    @ApiModelProperty(example = "20,00", dataType = "java.lang.String")
    private BigDecimal taxaFrete;

    @ApiModelProperty(example = "140,00", dataType = "java.lang.String")
    private BigDecimal valorTotal;

    @ApiModelProperty(example = "CONFIRMADO")
    private StatusPedido status;

    @ApiModelProperty(example = "2023-03-02T13:03:16.156Z")
    private OffsetDateTime dataCriacao;
    @ApiModelProperty(example = "2023-03-02T13:03:16.156Z")
    private OffsetDateTime dataConfirmacao;
    @ApiModelProperty(example = "2023-03-02T13:03:16.156Z")
    private OffsetDateTime dataCancelamento;
    @ApiModelProperty(example = "2023-03-02T13:03:16.156Z")
    private OffsetDateTime dataEntrega;


    private EnderecoDTO enderecoEntrega;
    private FormaPagamentoDTO formaPagamento;
    private UsuarioDTO cliente;
    private RestauranteResumoDTO restaurante;
    private List<ItemPedidoDTO> itens = new ArrayList<>();
}
