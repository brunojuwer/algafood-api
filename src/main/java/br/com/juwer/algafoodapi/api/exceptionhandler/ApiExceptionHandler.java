package br.com.juwer.algafoodapi.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

import br.com.juwer.algafoodapi.core.validation.ValidacaoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeEmUsoException;
import br.com.juwer.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.juwer.algafoodapi.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  private static final String MSG_ERRO_GENERICA_USUARIO_FINAL = 
    "Ocorreu um erro interno inesperado no sistema. "
  + "Tente novamente e se o problema persistir, entre em contato "
  + "com o administrador do sistema.";

@ExceptionHandler(Exception.class)
public ResponseEntity<Object> handleGlobalExceptions(Exception ex, WebRequest request){
  
  HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

  String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

  ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
  Problem problem = createProblemBuilder(
    status, problemType, detail, detail, LocalDateTime.now())
    .build();

  ex.printStackTrace();
 
  return handleExceptionInternal
    (ex, problem, new HttpHeaders(), status, request);
}


  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontrada(
      EntidadeNaoEncontradaException ex, WebRequest request) {
    
    ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
    String detail = ex.getMessage();
    HttpStatus status = HttpStatus.NOT_FOUND;
    
    Problem problem = createProblemBuilder(
      status, problemType, detail, detail, LocalDateTime.now())
      .build();
    
    return handleExceptionInternal(
        ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocioException(
        NegocioException ex, WebRequest request) {
    
    String detail = ex.getMessage();
    HttpStatus status = HttpStatus.BAD_REQUEST;
    ProblemType problemType = ProblemType.ERRO_NEGOCIO;
    
    Problem problem = createProblemBuilder(
      status, problemType, detail, detail, LocalDateTime.now())
      .build();
    
    return handleExceptionInternal(
        ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> handleEntidadeEmUsoException(NegocioException ex, WebRequest request) {

    String detail = ex.getMessage();
    HttpStatus status = HttpStatus.CONFLICT;
    ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
    
    Problem problem = createProblemBuilder(
      status, problemType, detail, detail, LocalDateTime.now())
      .build();
    
    return handleExceptionInternal(
          ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }
  
  @ExceptionHandler(ValidacaoException.class)
  public ResponseEntity<Object> handleValidacaoParcial(ValidacaoException ex, WebRequest request) {
    return handleValidationInternal(ex, HttpStatus.BAD_REQUEST, ex.getBindingResult(), request, new HttpHeaders());
  }
  
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex, HttpHeaders headers,
    HttpStatus status, WebRequest request) {
    
    return handleValidationInternal(ex, status, ex.getBindingResult(), request, headers);
    
  }

  public ResponseEntity<Object> handleValidationInternal(Exception ex, HttpStatus status, 
      BindingResult bindingResult, WebRequest request, HttpHeaders headers) {

    List<Problem.Object> problemFields = bindingResult.getAllErrors()
      .stream()
      .map(objectError -> {
        String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
        String name = objectError.getObjectName();

        if (objectError instanceof FieldError){
          name = ((FieldError) objectError).getField();
        }

        return Problem.Object.builder()
        .name(name)
        .userMessage(message)
        .build();
      }) 
      .collect(Collectors.toList());


    String detail = "Um ou mais campos estão inválidos." +
    "Faça o preenchimento correto e tente novamente";
      
    ProblemType problemType = ProblemType.DADOS_INVALIDOS;
    Problem problem = createProblemBuilder(
      status, problemType, detail, detail, LocalDateTime.now()).objects(problemFields)
      .build();

    return handleExceptionInternal(ex, problem, headers, status, request);
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
    
    Problem problem = createProblemBuilder(
      status, problemType, detail, MSG_ERRO_GENERICA_USUARIO_FINAL, LocalDateTime.now())
      .build();
    
    return handleExceptionInternal(ex, problem, headers, status, request);
  }
  
  private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    
    String path = joinPath(ex.getPath());
    
    String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
        + "que é de um tipo inválido. Corrija e informe o valor compátivel com o tipo '%s'", 
        path, ex.getValue(), ex.getTargetType().getSimpleName());
    
    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
    Problem problem = createProblemBuilder(
      status, problemType, detail, MSG_ERRO_GENERICA_USUARIO_FINAL, LocalDateTime.now())
      .build();
    
    return handleExceptionInternal(ex, problem, headers, status, request);
  }
  
  private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    String path = joinPath(ex.getPath());
    
    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
    String detail = String.format("A propriedade '%s' não existe. "
            + "Corrija ou remova essa propriedade e tente novamente.", path);

    Problem problem = createProblemBuilder(
      status, problemType, detail, MSG_ERRO_GENERICA_USUARIO_FINAL, LocalDateTime.now())
      .build();
    
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

    Problem problem = createProblemBuilder(
      status, problemType, detail, MSG_ERRO_GENERICA_USUARIO_FINAL, LocalDateTime.now())
      .build();
    
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {


    ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
    String detail = String
      .format("O recurso %s, que você tentou acessar, é inexistente.", 
      ex.getRequestURL());

    Problem problem = createProblemBuilder(
      status, problemType, detail, detail, LocalDateTime.now())
      .build();


    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal
    (Exception ex, @Nullable Object body, HttpHeaders headers,
        HttpStatus status, WebRequest request) {
    
    if (body == null) {
      body = Problem.builder()
        .title(status.getReasonPhrase())
        .status(status.value())
        .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
        .timestamp(LocalDateTime.now())
        .build();
    } else if (body instanceof String) {
      body = Problem.builder()
        .title((String) body)
        .status(status.value())
        .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
        .timestamp(LocalDateTime.now())
        .build();
    }
    
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }
  
  private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
      ProblemType problem, String detail, String userMessage, 
      LocalDateTime timestamp){

    return Problem.builder()
            .timestamp(timestamp)
            .status(status.value())
            .type(problem.getUrl())
            .title(problem.getTitle())
            .detail(detail)
            .userMessage(userMessage);
  }
  
  private String joinPath(List<Reference> references) {
    return references.stream()
        .map(ref -> ref.getFieldName())
        .collect(Collectors.joining("."));
  }  
}
