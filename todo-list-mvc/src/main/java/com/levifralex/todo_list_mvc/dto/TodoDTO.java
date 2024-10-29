package com.levifralex.todo_list_mvc.dto;

import java.sql.Timestamp;

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
	private Timestamp timestamp;
	private int state;

}
