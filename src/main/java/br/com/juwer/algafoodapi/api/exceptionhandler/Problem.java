package br.com.juwer.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;

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
  private String type;
  private String title;
  private String detail;

  private String userMessage;
  private LocalDateTime timestamp;
}
