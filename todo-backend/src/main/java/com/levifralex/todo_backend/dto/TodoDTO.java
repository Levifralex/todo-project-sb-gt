package com.levifralex.todo_backend.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDTO {

	private Long id;
	private String title;
	private String description;

	@Getter(AccessLevel.NONE)
	private Timestamp timestamp;

	private int state;
	private long userId;

	private UserDTO user;

	@JsonProperty
	public String getCreatedAt() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return dateFormat.format(timestamp);
	}

	// added for search (avoid fill input with previous record)
	private String descriptionSearch;

}
