package br.com.juwer.algafoodapi.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object>{
  
  private String campo;
  private String descricaoCampo;
  private String descricaoObrigatoria;


  @Override
  public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
    this.campo = constraintAnnotation.campo();
    this.descricaoCampo = constraintAnnotation.descricaoCampo();
    this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
  }

  @SuppressWarnings("null")
  @Override
  public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
    boolean valid = true;

    try {
      // faz um getTaxaFrete
      BigDecimal valor = (BigDecimal) BeanUtils
        .getPropertyDescriptor(objetoValidacao.getClass(), campo)
        .getReadMethod().invoke(objetoValidacao); 
      
      // faz um getNome 
      String descricao = (String) BeanUtils
        .getPropertyDescriptor(objetoValidacao.getClass(), descricaoCampo)
        .getReadMethod().invoke(objetoValidacao);

      // aqui verifica se o nome tem o Frete Grátis caso a taxaFrete sejá zero
      if(valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
        valid = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
      }
      return valid;

    } catch (Exception e) {
        throw new ValidationException();
    }
  }
}
