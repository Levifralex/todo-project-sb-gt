package com.levifralex.todo_api_rest.mapper;

import java.util.List;

import com.levifralex.todo_api_rest.dto.UserDTO;
import com.levifralex.todo_api_rest.entity.UserEntity;

public interface UserMapper {

	UserDTO toDTO(UserEntity e);

	UserEntity toEntity(UserDTO d);

	List<UserDTO> toDTO(List<UserEntity> lste);
	
}
