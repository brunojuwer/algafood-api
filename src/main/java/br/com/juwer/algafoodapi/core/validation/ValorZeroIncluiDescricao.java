package br.com.juwer.algafoodapi.core.validation;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

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
