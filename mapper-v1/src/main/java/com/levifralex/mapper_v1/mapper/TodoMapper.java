package com.levifralex.mapper_v1.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.levifralex.mapper_v1.dto.TodoDTO;
import com.levifralex.mapper_v1.entity.TodoEntity;

@Mapper(componentModel = "spring")
public interface TodoMapper {	

	TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);
	
	TodoDTO toDTO(TodoEntity todo);
	
	TodoEntity toEntity(TodoDTO todo);
	
	List<TodoDTO> toDTOList(List<TodoEntity> list);

}
