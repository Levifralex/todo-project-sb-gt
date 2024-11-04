package com.levifralex.todo_backend.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.levifralex.todo_backend.dto.TodoDTO;
import com.levifralex.todo_backend.entity.TodoEntity;

@Component
public class TodoMapperImpl implements TodoMapper {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TodoDTO toDTO(TodoEntity e) {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(e, TodoDTO.class);
	}

	@Override
	public TodoEntity toEntity(TodoDTO d) {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(d, TodoEntity.class);
	}

	@Override
	public List<TodoDTO> toDTO(List<TodoEntity> lste) {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return lste.stream().map(e -> this.toDTO(e)).toList();
	}

}
