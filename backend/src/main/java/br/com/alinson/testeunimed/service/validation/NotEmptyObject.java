package br.com.alinson.testeunimed.service.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyObjectValidator.class)
public @interface NotEmptyObject {
    String message() default "é obrigatório";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
