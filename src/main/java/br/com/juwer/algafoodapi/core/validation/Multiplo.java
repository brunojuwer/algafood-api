package br.com.juwer.algafoodapi.core.validation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD, FIELD, TYPE, ANNOTATION_TYPE, TYPE_USE, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MultiploValidator.class })
public @interface Multiplo {

  String message() default "Multiplo inválido";

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

  int numero();
}
