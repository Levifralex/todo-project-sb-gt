package com.levifralex.todo_list_mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levifralex.todo_list_mvc.dto.UserDTO;
import com.levifralex.todo_list_mvc.entity.UserEntity;
import com.levifralex.todo_list_mvc.mapper.UserMapper;
import com.levifralex.todo_list_mvc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserDTO> findAll() throws ServiceException {
		return userMapper.toDTO(userRepository.findAll());
	}

	@Override
	public Optional<UserDTO> findById(Long id) throws ServiceException {
		return null;
	}

	@Override
	public UserDTO save(UserDTO t) throws ServiceException {

		Optional<UserEntity> existingUser = userRepository.findByName(t.getName());
		Optional<UserEntity> existingEmail = userRepository.findByEmail(t.getEmail());

		if (existingUser.isPresent()) {
			throw new ServiceException("Usuario ya registrado, intenta con un nuevo usuario");
		}

		if (existingEmail.isPresent()) {
			throw new ServiceException("Correo ya registrado, intenta con un nuevo correo");
		}

		UserEntity userEntity = userMapper.toEntity(t);

		UserEntity retUserEntity = userRepository.save(userEntity);
		if (retUserEntity == null) {
			throw new ServiceException("Error to save User");
		}

		return userMapper.toDTO(retUserEntity);

	}

	@Override
	public UserDTO update(UserDTO t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws ServiceException {
		// TODO Auto-generated method stub

	}

}
