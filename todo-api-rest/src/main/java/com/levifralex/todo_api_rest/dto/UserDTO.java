package com.levifralex.todo_api_rest.dto;

import com.levifralex.todo_api_rest.annotations.UserEmail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

	private Long id;
	private String name;

	@UserEmail
	private String email;

	private String password;

}
