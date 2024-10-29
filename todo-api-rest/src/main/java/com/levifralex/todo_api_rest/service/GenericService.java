package com.levifralex.todo_api_rest.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

	List<T> findAll() throws ServiceException;

	Optional<T> findById(Long id) throws ServiceException;

	T save(T t) throws ServiceException;

	T update(T t) throws ServiceException;

	void delete(Long id) throws ServiceException;

}