package com.levifralex.mapper_v1.service;

import java.util.List;

import com.levifralex.mapper_v1.dto.TodoDTO;

public interface TodoService extends GenericService<TodoDTO> {

	void deleteLogic(Long id) throws ServiceException;

	void customUpdate(TodoDTO t) throws ServiceException;
	
	List<TodoDTO> findByLikeDescription(String description) throws ServiceException;

}
