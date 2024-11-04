package com.levifralex.todo_backend.mapper;

import java.util.List;

import com.levifralex.todo_backend.dto.TodoDTO;
import com.levifralex.todo_backend.entity.TodoEntity;

public interface TodoMapper {

	TodoDTO toDTO(TodoEntity e);

	TodoEntity toEntity(TodoDTO d);

	List<TodoDTO> toDTO(List<TodoEntity> lste);

}
