package br.com.juwer.algafoodapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

// caso algum dos campos sejá null não será representado na resposta JSON
@JsonInclude(Include.NON_NULL)
@Builder
@Getter
@Schema(name = "Problema")
public class Problem {

  @Schema(example = "404")
  private Integer status;

  @Schema(example = "2023-05-25T18:32:11.919111612Z")
  private OffsetDateTime timestamp;

  @Schema(example = "https://algafood.com.br/dados-invalidos")
  private String type;

  @Schema(example = "Dados inválidos")
  private String title;

  @Schema(example = "Um ou mais campos estão inválidos")
  private String detail;

  @Schema(example = "Um ou mais campos estão inválidos")
  private String userMessage;

  @Schema(description = "Lista de objetos ou campos que geraram o erro")
  private List<Object> objects;

  @Getter
  @Builder
  @Schema(name = "ObjetoProblema")
  public static class Object {

    @Schema(example = "preco")
    private String name;

    @Schema(example = "Formato inválido")
    private String userMessage;
  }
}