package com.levifralex.todo_api_rest.mapper;

import java.util.List;

import com.levifralex.todo_api_rest.dto.TodoDTO;
import com.levifralex.todo_api_rest.entity.TodoEntity;

public interface TodoMapper {

	TodoDTO toDTO(TodoEntity e);

	TodoEntity toEntity(TodoDTO d);

	List<TodoDTO> toDTO(List<TodoEntity> lste);

}