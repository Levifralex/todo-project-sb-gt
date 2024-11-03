package com.levifralex.todo_api_rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.levifralex.todo_api_rest.config.JwtService;
import com.levifralex.todo_api_rest.dto.UserDTO;
import com.levifralex.todo_api_rest.dto.UserLogin;
import com.levifralex.todo_api_rest.entity.UserEntity;
import com.levifralex.todo_api_rest.exceptions.ResourceNotFoundException;
import com.levifralex.todo_api_rest.mapper.UserMapper;
import com.levifralex.todo_api_rest.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Override
	public List<UserDTO> findAll() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<UserDTO> findById(Long id) throws ResourceNotFoundException {
		UserEntity userEntity = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("User not foud with id %s", id)));
		return Optional.ofNullable(userMapper.toDTO(userEntity));

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> user = userRepository.findByName(username);
		UserEntity usuario = user.get();

		return new User(usuario.getName(), usuario.getPassword(), new ArrayList<>());
	}

	@Override
	public Map<String, String> loginByUserName(UserLogin credentials) throws ResourceNotFoundException {
		String username = credentials.getUsername();
		String password = credentials.getPassword();

		Optional<UserEntity> user = userRepository.findByName(username);

		if (!user.isPresent()) {
			throw new ResourceNotFoundException("Usuario no encontrado en la base de datos");
		} else {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			String token = jwtService.generateToken(credentials);

			Map<String, String> responce = new HashMap<>();

			responce.put("token", token);

			return responce;
		}
	}

}
