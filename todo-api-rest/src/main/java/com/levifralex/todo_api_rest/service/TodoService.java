package com.levifralex.todo_api_rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.levifralex.todo_api_rest.dto.TodoDTO;
import com.levifralex.todo_api_rest.exceptions.ResourceNotFoundException;

public interface TodoService extends GenericService<TodoDTO> {

	void deleteLogic(Long id) throws ServiceException;

	void customUpdate(TodoDTO t) throws ServiceException, ResourceNotFoundException;

	Page<TodoDTO> findAllPaging(Pageable pageable) throws ServiceException;

}
