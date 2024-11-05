package com.levifralex.todo_api_rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levifralex.todo_api_rest.dto.UserDTO;
import com.levifralex.todo_api_rest.dto.UserLogin;
import com.levifralex.todo_api_rest.exceptions.ResourceNotFoundException;
import com.levifralex.todo_api_rest.service.ServiceException;
import com.levifralex.todo_api_rest.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${api.basicUrl}/auth")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLogin credentials) throws ResourceNotFoundException {
		Map<String, String> response = userService.loginByUserName(credentials);
		return ResponseEntity.ok(response);

	}

	@PostMapping("/register")
	public ResponseEntity<?> save(@RequestBody @Valid UserDTO user, BindingResult result)
			throws ResourceNotFoundException {
		Map<String, Object> body = new HashMap<>();

		if (result.hasErrors()) {
			body.put("error", result.getAllErrors());
			return ResponseEntity.badRequest().body(body);
		}

		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			UserDTO oUser = userService.save(user);
			if (oUser != null) {
				return new ResponseEntity<>(oUser, HttpStatus.CREATED);
			}
			body.put("error", "User register error");
			return ResponseEntity.badRequest().body(body);
		} catch (ServiceException e) {
			body.put("success", false);
			body.put("error", e.getMessage());
			return ResponseEntity.internalServerError().body(body);
		}

	}

}
