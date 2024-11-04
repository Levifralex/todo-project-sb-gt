package com.levifralex.todo_backend.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levifralex.todo_backend.dto.TodoDTO;
import com.levifralex.todo_backend.dto.UserDTO;
import com.levifralex.todo_backend.entity.TodoEntity;
import com.levifralex.todo_backend.enums.TodoStateEnum;
import com.levifralex.todo_backend.exceptions.ResourceNotFoundException;
import com.levifralex.todo_backend.mapper.TodoMapper;
import com.levifralex.todo_backend.mapper.UserMapper;
import com.levifralex.todo_backend.repository.TodoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private TodoMapper todoMapper;

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<TodoDTO> findAll() throws ServiceException {
		return todoMapper.toDTO(todoRepository.findAllActives());
	}

	@Override
	public Optional<TodoDTO> findById(Long id) throws ResourceNotFoundException {
		TodoEntity todoEntity = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Todo not foud with id %s", id)));
		return Optional.ofNullable(todoMapper.toDTO(todoEntity));
	}

	@Override
	public TodoDTO save(TodoDTO t) throws ServiceException, ResourceNotFoundException {

		TodoEntity todoEntity = todoMapper.toEntity(t);
		todoEntity.setState(TodoStateEnum.ACTIVE.getCode());

		Optional<UserDTO> userOpt = userService.findById(t.getUserId());

		if (userOpt.isPresent()) {
			todoEntity.setUser(userMapper.toEntity(userOpt.get()));
		} else {
			throw new ResourceNotFoundException("Usuario no encontrado con ID: " + t.getUserId());
		}

		TodoEntity retTodoEntity = todoRepository.save(todoEntity);
		if (retTodoEntity == null) {
			throw new ServiceException("Error to save Todo");
		}
		return todoMapper.toDTO(retTodoEntity);

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
			return todoMapper.toDTO(retTodoEntity);

		} else {

			TodoEntity todoEntity = todoMapper.toEntity(t);
			todoEntity.setState(TodoStateEnum.ACTIVE.getCode());

			TodoEntity retTodoEntity = todoRepository.save(todoEntity);

			if (retTodoEntity == null) {
				throw new ServiceException("Error to save Todo");
			}
			return todoMapper.toDTO(retTodoEntity);

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
			return todoMapper.toDTO(todoRepository.findByLikeDescription("%" + description + "%"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

}
