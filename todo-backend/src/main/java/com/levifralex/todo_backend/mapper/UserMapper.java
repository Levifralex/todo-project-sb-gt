package com.levifralex.todo_backend.mapper;

import java.util.List;

import com.levifralex.todo_backend.dto.UserDTO;
import com.levifralex.todo_backend.entity.UserEntity;

public interface UserMapper {

	UserDTO toDTO(UserEntity e);

	UserEntity toEntity(UserDTO d);

	List<UserDTO> toDTO(List<UserEntity> lste);
	
}
