package br.com.juwer.algafoodapi.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontrada(
      EntidadeNaoEncontradaException ex, WebRequest request) {
    
    ProblemType problemType = ProblemType.ENTIDADE_NAO_ECONTRADA;
    String detailString = ex.getMessage();
    HttpStatus status = HttpStatus.NOT_FOUND;
    
    Problem problem = createProblemBuilder(status, problemType, detailString).build();
    
    return handleExceptionInternal(
        ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocioException(
        NegocioException ex, WebRequest request) {
    
    String detail = ex.getMessage();
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ProblemType problemType = ProblemType.ERRO_NEGOCIO;
    
    Problem problem = createProblemBuilder(status, problemType, detail).build();
    
    return handleExceptionInternal(
        ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> handleEntidadeEmUsoException(NegocioException ex, WebRequest request) {

    String detail = ex.getMessage();
    HttpStatus status = HttpStatus.CONFLICT;
    ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
    
    Problem problem = createProblemBuilder(status, problemType, detail).build();
    
    return handleExceptionInternal(
          ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
  
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    
    Throwable rootCause = ExceptionUtils.getRootCause(ex);
    
    if(rootCause instanceof InvalidFormatException) {
      return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
    } else if (rootCause instanceof PropertyBindingException) {
      return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request); 
    }
    
    String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe";
    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
    
    Problem problem = createProblemBuilder(status, problemType, detail).build();
    
    return handleExceptionInternal(ex, problem, headers, status, request);
  }
  
  private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    
    String path = joinPath(ex.getPath());
    
    String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
        + "que é de um tipo inválido. Corrija e informe o valor compátivel com o tipo '%s'", 
        path, ex.getValue(), ex.getTargetType().getSimpleName());
    
    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
    Problem problem = createProblemBuilder(status, problemType, detail).build();
    
    return handleExceptionInternal(ex, problem, headers, status, request);
  }
  
  private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    String path = joinPath(ex.getPath());
    
    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
    String detail = String.format("A propriedade '%s' não existe. "
            + "Corrija ou remova essa propriedade e tente novamente.", path);

    Problem problem = createProblemBuilder(status, problemType, detail).build();
    
    return handleExceptionInternal(ex, problem, headers, status, request);
  } 

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {

        if(ex instanceof MethodArgumentTypeMismatchException){
          return handleMethodArgumentTypeMismatchException(
            (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }
        
        return super.handleTypeMismatch(ex, headers, status, request);
  }

  @SuppressWarnings("null")
  private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
    MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus 
    status, WebRequest request) {

    ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
    
    String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é um tipo inválido. " + 
      "Corrija e informe um valor compatível com o tipo %s.", 
      ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

    Problem problem = createProblemBuilder(status, problemType, detail).build();
    
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal
    (Exception ex, @Nullable Object body, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
    
    if (body == null) {
      body = Problem.builder().title(status.getReasonPhrase())
          .status(status.value()).build();
    } else if (body instanceof String) {
      body = Problem.builder().title((String) body)
          .status(status.value()).build();
    }
    
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }
  
  private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
      ProblemType problem, String detail){
    return Problem.builder()
            .status(status.value())
            .type(problem.getUrl())
            .title(problem.getTitle())
            .detail(detail);
  }
  
  private String joinPath(List<Reference> references) {
    return references.stream()
        .map(ref -> ref.getFieldName())
        .collect(Collectors.joining("."));
  }  
}
