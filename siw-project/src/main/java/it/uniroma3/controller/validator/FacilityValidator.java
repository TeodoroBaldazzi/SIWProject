package it.uniroma3.controller.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.model.Facility;

@Component
public class FacilityValidator implements Validator{

	
	@Override
	public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxCapacity", "required");

	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Facility.class.equals(aClass);
	}

}
