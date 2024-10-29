package com.levifralex.todo_api_rest.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.levifralex.todo_api_rest.exceptions.dto.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/*@ExceptionHandler(Exception.class)
	public ResponseEntity<GenericResponse<CustomErrorResponse>> handleAllExceptions(Exception ex, WebRequest req) {

		CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(),
				req.getDescription(false));
		return new ResponseEntity<>(new GenericResponse<>(500, "BACKEND-ERROR", Arrays.asList(errorResponse)),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}*/

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorMessage> recouserNotFoundException(ResourceNotFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, Object> errors = new LinkedHashMap<>();
		errors.put("message", ex.getMessage());
		errors.put("error", HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
