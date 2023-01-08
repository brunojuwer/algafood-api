package br.com.juwer.algafoodapi.core.validation;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidacaoException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  private BindingResult bindingResult;
}
