package com.levifralex.todo_api_rest.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = StateValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface TodoState {
	
	String message() default "Invalid state";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
