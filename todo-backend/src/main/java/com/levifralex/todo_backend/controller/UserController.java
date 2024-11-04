package com.levifralex.todo_backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levifralex.todo_backend.dto.UserDTO;
import com.levifralex.todo_backend.exceptions.ResourceNotFoundException;
import com.levifralex.todo_backend.service.ServiceException;
import com.levifralex.todo_backend.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${api.basicUrl}/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() throws ServiceException {
		List<UserDTO> users = userService.findAll();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public Optional<UserDTO> findById(@PathVariable Long id) throws ResourceNotFoundException, ServiceException {
		return userService.findById(id);
	}

}
