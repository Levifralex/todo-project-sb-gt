package com.levifralex.todo_api_rest.dto;

import lombok.Data;

@Data
public class CustomPageable {

	private int pageNumber;
	private int pageSize;
	private String[] fields;
	private String order;
	
}
