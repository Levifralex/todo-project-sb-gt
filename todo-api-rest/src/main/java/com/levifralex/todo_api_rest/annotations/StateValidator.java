package com.levifralex.todo_api_rest.annotations;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.levifralex.todo_api_rest.enums.TodoStateEnum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StateValidator implements ConstraintValidator<TodoState, Integer> {

	@Override
	public boolean isValid(Integer state, ConstraintValidatorContext context) {
		if (state == null) {
			return false;
		}

		int stateExists = Arrays.stream(TodoStateEnum.values()).filter(e -> e.getCode() == state)
				.collect(Collectors.toList()).size();

		return stateExists > 0;

	}

}
