package br.com.juwer.algafoodapi.core.validation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.PositiveOrZero;

@Target({METHOD, FIELD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { })
@PositiveOrZero
public @interface TaxaFrete {
  
  @OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
  String message() default "{TaxaFrete.invalida}";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };
}
