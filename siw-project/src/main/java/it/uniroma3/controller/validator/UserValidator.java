package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.model.User;

@Component
public class UserValidator implements Validator{

	@Override
	public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");

	}

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

}
