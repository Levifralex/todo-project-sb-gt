package com.levifralex.mapper_v2.mapper;

import java.util.List;

import com.levifralex.mapper_v2.dto.TodoDTO;
import com.levifralex.mapper_v2.entity.TodoEntity;

public interface TodoMapper {

	TodoDTO toDTO(TodoEntity todo);

	TodoEntity toEntity(TodoDTO todo);

	List<TodoDTO> toDTOList(List<TodoEntity> list);

}
