package br.com.juwer.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

  MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem Incompreensível"),
  RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
  ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
  ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
  ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
  PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido");
  
  private String url;
  private String title;
  
  ProblemType(String uri, String title) {
    this.url = "http://localhost:8081" + uri;
    this.title = title;
  }
}
