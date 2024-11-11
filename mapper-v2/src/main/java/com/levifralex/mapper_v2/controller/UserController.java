package com.levifralex.mapper_v2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levifralex.mapper_v2.dto.UserDTO;
import com.levifralex.mapper_v2.exceptions.ResourceNotFoundException;
import com.levifralex.mapper_v2.service.ServiceException;
import com.levifralex.mapper_v2.service.UserService;

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