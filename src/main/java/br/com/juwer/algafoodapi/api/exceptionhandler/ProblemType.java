package br.com.juwer.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

  ENTIDADE_NAO_ECONTRADA("/entidade-nao-econtrada", "Entidade não encontrada"),
  ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso");
  
  
  private String uri;
  private String title;
  
  ProblemType(String path, String title) {
    this.uri = "http://localhost:8081" + path;
    this.title = title;
  }
}
