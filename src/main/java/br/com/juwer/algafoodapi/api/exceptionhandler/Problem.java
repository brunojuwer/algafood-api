package br.com.juwer.algafoodapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

// caso algum dos campos sejá null não será representado na resposta JSON
@JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class Problem {
  
  private Integer status;
  private OffsetDateTime timestamp;
  private String type;
  private String title;
  private String detail;
  private String userMessage;
  private List<Object> objects;

  @Getter
  @Builder
  public static class Object {
    private String name;
    private String userMessage;
  }

}
