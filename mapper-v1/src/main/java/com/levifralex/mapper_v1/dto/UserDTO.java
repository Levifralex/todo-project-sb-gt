package com.levifralex.mapper_v1.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

	private Long id;
	private String name;
	private String email;

	@Getter(AccessLevel.NONE)
	private String password;

}
