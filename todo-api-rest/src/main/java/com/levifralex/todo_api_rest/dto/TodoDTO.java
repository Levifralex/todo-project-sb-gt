package com.levifralex.todo_api_rest.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.levifralex.todo_api_rest.annotations.TodoState;

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

	@TodoState(message = "State not valid, use only (0, 1)")
	private int state;
	
	private long userId;

	@JsonProperty
	public String getCreatedAt() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return dateFormat.format(timestamp);
	}

}
