package com.levifralex.mapper_v1.service;

import java.util.List;
import java.util.Optional;

import com.levifralex.mapper_v1.exceptions.ResourceNotFoundException;

public interface GenericService<T> {

	List<T> findAll() throws ServiceException;

	Optional<T> findById(Long id) throws ResourceNotFoundException;

	T save(T t) throws ServiceException, ResourceNotFoundException;

	T update(T t) throws ServiceException;

	void delete(Long id) throws ServiceException;

}