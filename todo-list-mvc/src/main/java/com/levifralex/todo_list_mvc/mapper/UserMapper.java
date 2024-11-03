package com.levifralex.todo_list_mvc.mapper;

import java.util.List;

import com.levifralex.todo_list_mvc.dto.UserDTO;
import com.levifralex.todo_list_mvc.entity.UserEntity;

public interface UserMapper {

	UserDTO toDTO(UserEntity e);

	UserEntity toEntity(UserDTO d);

	List<UserDTO> toDTO(List<UserEntity> lste);
	
}
