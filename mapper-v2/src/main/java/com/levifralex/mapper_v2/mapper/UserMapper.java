package com.levifralex.mapper_v2.mapper;

import java.util.List;

import com.levifralex.mapper_v2.dto.UserDTO;
import com.levifralex.mapper_v2.entity.UserEntity;

public interface UserMapper {

	UserDTO toDTO(UserEntity todo);

	UserEntity toEntity(UserDTO todo);

	List<UserDTO> toDTOList(List<UserEntity> list);

}
