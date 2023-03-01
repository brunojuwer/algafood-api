package br.com.juwer.algafoodapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

// caso algum dos campos sejá null não será representado na resposta JSON
@JsonInclude(Include.NON_NULL)
@Builder
@Getter
@ApiModel("Problema")
public class Problem {

  @ApiModelProperty(example = "400", position = 1)
  private Integer status;

  @ApiModelProperty(example = "2023-03-01T19:19:44.790947965Z", position = 5)
  private OffsetDateTime timestamp;

  @ApiModelProperty(example = "http://localhost:8081/parametro-invalido", position = 10)
  private String type;

  @ApiModelProperty(example = "Parâmetro inválido", position = 15)
  private String title;

  @ApiModelProperty(example = "O parâmetro de URL 'cidadeId' recebeu o valor '62s', que é um tipo inválido. Corrija e informe um valor compatível com o tipo Long"
    , position = 20)
  private String detail;

  @ApiModelProperty(example = "Um ou mais campos estão inválidos.Faça o preenchimento correto e tente novamente", position = 25)
  private String userMessage;

  @ApiModelProperty(value = "Objetos ou campos que gerar o erro", position = 30)
  private List<Object> objects;

  @Getter
  @Builder
  @ApiModel("ObjetoProblema")
  public static class Object {

    @ApiModelProperty(example = "Preço")
    private String name;

    @ApiModelProperty(example = "O preço é obrigatório")
    private String userMessage;
  }
}