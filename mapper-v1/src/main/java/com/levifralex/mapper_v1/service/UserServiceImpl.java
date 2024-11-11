package com.levifralex.mapper_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levifralex.mapper_v1.dto.UserDTO;
import com.levifralex.mapper_v1.entity.UserEntity;
import com.levifralex.mapper_v1.exceptions.ResourceNotFoundException;
import com.levifralex.mapper_v1.mapper.UserMapper;
import com.levifralex.mapper_v1.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDTO> findAll() throws ServiceException {
		return UserMapper.INSTANCE.toDTOList(userRepository.findAll());
	}

	@Override
	public Optional<UserDTO> findById(Long id) throws ResourceNotFoundException {
		UserEntity userEntity = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("User not foud with id %s", id)));
		return Optional.ofNullable(UserMapper.INSTANCE.toDTO(userEntity));
	}

	@Override
	public UserDTO save(UserDTO t) throws ServiceException {

		/*Optional<UserEntity> existingUser = userRepository.findByName(t.getName());
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

		return userMapper.toDTO(retUserEntity);*/
		return null;

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
