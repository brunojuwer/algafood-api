package br.com.juwer.algafoodapi.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
    
    return handleExceptionInternal(
        ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
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
            .type(problem.getUri())
            .title(problem.getTitle())
            .detail(detail);
  }
}
