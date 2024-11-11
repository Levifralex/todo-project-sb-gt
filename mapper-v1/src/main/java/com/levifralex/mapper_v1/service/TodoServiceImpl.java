package com.levifralex.mapper_v1.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levifralex.mapper_v1.dto.TodoDTO;
import com.levifralex.mapper_v1.dto.UserDTO;
import com.levifralex.mapper_v1.entity.TodoEntity;
import com.levifralex.mapper_v1.enums.TodoStateEnum;
import com.levifralex.mapper_v1.exceptions.ResourceNotFoundException;
import com.levifralex.mapper_v1.mapper.TodoMapper;
import com.levifralex.mapper_v1.mapper.UserMapper;
import com.levifralex.mapper_v1.repository.TodoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private UserService userService;

	@Override
	public List<TodoDTO> findAll() throws ServiceException {
		return TodoMapper.INSTANCE.toDTOList(todoRepository.findAllActives());
	}

	@Override
	public Optional<TodoDTO> findById(Long id) throws ResourceNotFoundException {
		TodoEntity todoEntity = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Todo not foud with id %s", id)));
		return Optional.ofNullable(TodoMapper.INSTANCE.toDTO(todoEntity));
	}

	@Override
	public TodoDTO save(TodoDTO t) throws ServiceException, ResourceNotFoundException {

		TodoEntity todoEntity = TodoMapper.INSTANCE.toEntity(t);
		todoEntity.setState(TodoStateEnum.ACTIVE.getCode());

		Optional<UserDTO> userOpt = userService.findById(t.getUserId());

		if (userOpt.isPresent()) {
			todoEntity.setUser(UserMapper.INSTANCE.toEntity(userOpt.get()));
		} else {
			throw new ResourceNotFoundException("Usuario no encontrado con ID: " + t.getUserId());
		}

		TodoEntity retTodoEntity = todoRepository.save(todoEntity);
		if (retTodoEntity == null) {
			throw new ServiceException("Error to save Todo");
		}

		return TodoMapper.INSTANCE.toDTO(retTodoEntity);
	}

	@Override
	public TodoDTO update(TodoDTO t) throws ServiceException {

		int stateExists = Arrays.stream(TodoStateEnum.values()).filter(e -> e.getCode() == t.getState())
				.collect(Collectors.toList()).size();

		if (stateExists == 0) {
			throw new RuntimeException("State doesnt exists");
		}

		Optional<TodoEntity> optTodo = todoRepository.findById(t.getId());

		if (optTodo.isPresent()) {

			TodoEntity oTodo = optTodo.get();
			oTodo.setTitle(t.getTitle());
			oTodo.setDescription(t.getDescription());
			oTodo.setState(t.getState());

			TodoEntity retTodoEntity = todoRepository.save(oTodo);

			if (retTodoEntity == null) {
				throw new ServiceException("Error to save Todo");
			}

			return TodoMapper.INSTANCE.toDTO(retTodoEntity);

		} else {

			TodoEntity todoEntity = TodoMapper.INSTANCE.toEntity(t);
			todoEntity.setState(TodoStateEnum.ACTIVE.getCode());

			TodoEntity retTodoEntity = todoRepository.save(todoEntity);

			if (retTodoEntity == null) {
				throw new ServiceException("Error to save Todo");
			}

			return TodoMapper.INSTANCE.toDTO(retTodoEntity);

		}

	}

	@Override
	public void customUpdate(TodoDTO t) throws ServiceException {

		Optional<TodoEntity> optTodo = todoRepository.findById(t.getId());

		if (optTodo.isPresent()) {
			TodoEntity oTodo = optTodo.get();
			oTodo.setTitle(t.getTitle());
			oTodo.setDescription(t.getDescription());
			oTodo.setState(t.getState());
			todoRepository.save(oTodo);
		} else {
			throw new RuntimeException("Todo not found");
		}

	}

	@Override
	public void delete(Long id) throws ServiceException {

		todoRepository.findById(id)
				.orElseThrow(() -> new ServiceException(String.format("Todo not foud with id %s", id)));
		todoRepository.deleteById(id);

	}

	@Transactional
	@Override
	public void deleteLogic(Long id) throws ServiceException {

		todoRepository.findById(id)
				.orElseThrow(() -> new ServiceException(String.format("Todo not foud with id %s", id)));
		todoRepository.deleteLogic(id);

	}

	@Override
	public List<TodoDTO> findByLikeDescription(String description) throws ServiceException {
		try {
			return TodoMapper.INSTANCE.toDTOList(todoRepository.findByLikeDescription("%" + description + "%"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

}
