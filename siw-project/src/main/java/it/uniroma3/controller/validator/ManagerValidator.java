package it.uniroma3.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.model.FacilityManager;

@Component
public class ManagerValidator implements Validator{

	@Override
	public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");

	}

	@Override
	public boolean supports(Class<?> aClass) {
		return FacilityManager.class.equals(aClass);
	}

}
