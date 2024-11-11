package com.levifralex.mapper_v1.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.levifralex.mapper_v1.dto.UserDTO;
import com.levifralex.mapper_v1.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDTO toDTO(UserEntity user);
	
	UserEntity toEntity(UserDTO user);
	
	List<UserDTO> toDTOList(List<UserEntity> list);

}
