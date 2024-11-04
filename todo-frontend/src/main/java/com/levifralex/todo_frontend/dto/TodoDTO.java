package com.levifralex.todo_frontend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDTO {

	private Long id;
	private String title;
	private String description;

	private int state;
	private long userId;

	private UserDTO user;
	
	private String createdAt;

	// added for search (avoid fill input with previous record)
	private String descriptionSearch;

}
