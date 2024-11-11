package com.levifralex.mapper_v2.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.levifralex.mapper_v2.dto.UserDTO;
import com.levifralex.mapper_v2.entity.TodoEntity;
import com.levifralex.mapper_v2.entity.UserEntity;

@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserDTO toDTO(UserEntity user) {
		return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
	}

	@Override
	public UserEntity toEntity(UserDTO user) {
		// revisar
		return new UserEntity(user.getId(), user.getName(), user.getEmail(), "", new ArrayList<TodoEntity>());
	}

	@Override
	public List<UserDTO> toDTOList(List<UserEntity> list) {
		return list.stream().map(user -> this.toDTO(user)).toList();
	}

}
