package com.levifralex.todo_api_rest.annotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<UserEmail, String> {

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email == null || email.isBlank()) {
            return false;
        }
		
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
		
	}

}
