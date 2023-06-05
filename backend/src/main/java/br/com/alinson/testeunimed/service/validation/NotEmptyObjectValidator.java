package br.com.alinson.testeunimed.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyObjectValidator implements ConstraintValidator<NotEmptyObject, Object> {

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
		return object != null;
	}

	@Override
	public void initialize(NotEmptyObject constraintAnnotation) {
	}
}
