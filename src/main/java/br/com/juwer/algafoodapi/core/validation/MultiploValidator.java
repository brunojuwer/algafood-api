package br.com.juwer.algafoodapi.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number>{

  private int numeroMultiplo; // numero que foi passado para a anotação 
  
  @Override
  public void initialize(Multiplo constraintAnnotation) {
    this.numeroMultiplo = constraintAnnotation.numero();
  }

  @Override
  public boolean isValid(Number value, ConstraintValidatorContext context) {
    boolean valido = true;
    // value é o numero da requisição
    if(value != null) {
      var valorDecimal = BigDecimal.valueOf(value.doubleValue());
      var multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo);

      var resto = valorDecimal.remainder(multiploDecimal);

      // ZERO.compareTo() retorna 0 se o ZERO é igual a resto
      valido = BigDecimal.ZERO.compareTo(resto) == 0; 
    }

    return valido;
  }

}
