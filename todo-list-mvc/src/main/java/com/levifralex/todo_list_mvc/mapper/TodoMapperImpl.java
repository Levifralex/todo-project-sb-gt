package com.levifralex.todo_list_mvc.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.levifralex.todo_list_mvc.dto.TodoDTO;
import com.levifralex.todo_list_mvc.entity.TodoEntity;

@Component
public class TodoMapperImpl implements TodoMapper {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TodoDTO toDTO(TodoEntity e) {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		//modelMapper.createTypeMap(TodoEntity.class, TodoDTO.class).addMapping(TodoEntity::getUser, TodoDTO::setUser);
		return modelMapper.map(e, TodoDTO.class);
	}

	@Override
	public TodoEntity toEntity(TodoDTO d) {
		return modelMapper.map(d, TodoEntity.class);
	}

	@Override
	public List<TodoDTO> toDTO(List<TodoEntity> lste) {
		return lste.stream().map(e -> this.toDTO(e)).toList();
	}

}
