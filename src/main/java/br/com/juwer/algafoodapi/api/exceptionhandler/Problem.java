package br.com.juwer.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

// caso algum dos campos sejá null não será representado na resposta JSON
@JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class Problem {
  
  private Integer status;
  private LocalDateTime timestamp;
  private String type;
  private String title;
  private String detail;
  private String userMessage;
  private List<Field> fields;

  @Getter
  @Builder
  public static class Field {
    private String name;
    private String userMessage;
  }

}
