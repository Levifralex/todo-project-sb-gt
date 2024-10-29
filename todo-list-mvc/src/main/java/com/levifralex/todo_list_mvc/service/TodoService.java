package com.levifralex.todo_list_mvc.service;

import com.levifralex.todo_list_mvc.dto.TodoDTO;

public interface TodoService extends GenericService<TodoDTO> {

	void deleteLogic(Long id) throws ServiceException;
	void customUpdate(TodoDTO t) throws ServiceException;

}
