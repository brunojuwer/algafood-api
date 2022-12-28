package br.com.juwer.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

  MESAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem Incompreensível"),
  ENTIDADE_NAO_ECONTRADA("/entidade-nao-econtrada", "Entidade não encontrada"),
  ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
  ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");         
  
  private String uri;
  private String title;
  
  ProblemType(String path, String title) {
    this.uri = "http://localhost:8081" + path;
    this.title = title;
  }
}
