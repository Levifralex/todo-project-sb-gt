package com.levifralex.todo_api_rest.service;

import java.util.List;
import java.util.Optional;

import com.levifralex.todo_api_rest.exceptions.ResourceNotFoundException;

public interface GenericService<T> {

	List<T> findAll() throws ServiceException;

	Optional<T> findById(Long id) throws ResourceNotFoundException;

	T save(T t) throws ServiceException, ResourceNotFoundException;

	T update(T t) throws ServiceException;

	void delete(Long id) throws ServiceException;

}