package com.levifralex.mapper_v2.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.levifralex.mapper_v2.dto.TodoDTO;
import com.levifralex.mapper_v2.dto.UserDTO;
import com.levifralex.mapper_v2.entity.TodoEntity;

@Component
public class TodoMapperImpl implements TodoMapper {

	@Override
	public TodoDTO toDTO(TodoEntity todo) {
		// TODO Auto-generated method stub
		// return null;

		// return new TodoDTO(todo.getId(), todo.getTitle() ,todo.getDescription(),
		// todo.getTimestamp(), 0, 0, null, null);

		return new TodoDTO(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getTimestamp(), todo.getState(),
				todo.getUser().getId(), new UserDTO(), "");
	}

	@Override
	public TodoEntity toEntity(TodoDTO todo) {
		// TODO Auto-generated method stub
		// return null;
		return new TodoEntity(todo.getId(), todo.getTitle(), todo.getDescription(), null, todo.getState(), null);

	}

	@Override
	public List<TodoDTO> toDTOList(List<TodoEntity> list) {
		// TODO Auto-generated method stub
		// return null;
		return list.stream().map(todo -> this.toDTO(todo)).toList();
	}

}
