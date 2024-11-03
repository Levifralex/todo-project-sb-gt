package com.levifralex.todo_list_mvc.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.levifralex.todo_list_mvc.dto.UserDTO;
import com.levifralex.todo_list_mvc.entity.UserEntity;

@Component
public class UserMapperImpl implements UserMapper {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO toDTO(UserEntity e) {
		return modelMapper.map(e, UserDTO.class);
	}

	@Override
	public UserEntity toEntity(UserDTO d) {
		return modelMapper.map(d, UserEntity.class);
	}

	@Override
	public List<UserDTO> toDTO(List<UserEntity> lste) {
		return lste.stream().map(e -> this.toDTO(e)).toList();
	}

}
