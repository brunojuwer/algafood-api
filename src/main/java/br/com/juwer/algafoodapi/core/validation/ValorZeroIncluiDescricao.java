package br.com.juwer.algafoodapi.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValorZeroIncluiDescricaoValidator.class })
public @interface ValorZeroIncluiDescricao {
  
  String message() default  "Descrição obrigatória inválida";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default { };

  String campo();
  String descricaoCampo();
  String descricaoObrigatoria();
}
