package com.levifralex.todo_list_mvc.mapper;

import java.util.List;

import com.levifralex.todo_list_mvc.dto.TodoDTO;
import com.levifralex.todo_list_mvc.entity.TodoEntity;

public interface TodoMapper {

	TodoDTO toDTO(TodoEntity e);

	TodoEntity toEntity(TodoDTO d);

	List<TodoDTO> toDTO(List<TodoEntity> lste);

}
