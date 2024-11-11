package com.levifralex.mapper_v2.exceptions.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessage {

	private HttpStatus httpStatus;
    private String message;
	
}
