package com.levifralex.todo_backend.service;

import java.util.List;

import com.levifralex.todo_backend.dto.TodoDTO;

public interface TodoService extends GenericService<TodoDTO> {

	void deleteLogic(Long id) throws ServiceException;

	void customUpdate(TodoDTO t) throws ServiceException;
	
	List<TodoDTO> findByLikeDescription(String description) throws ServiceException;

}
