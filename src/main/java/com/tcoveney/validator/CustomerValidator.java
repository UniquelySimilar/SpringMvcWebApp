package com.tcoveney.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tcoveney.model.Customer;

public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required");
		// TODO: Add validation for zipcode, phone numbers, and email
	}

}
