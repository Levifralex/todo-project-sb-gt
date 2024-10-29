package com.levifralex.todo_api_rest.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.levifralex.todo_api_rest.dto.UserDTO;
import com.levifralex.todo_api_rest.dto.UserLogin;
import com.levifralex.todo_api_rest.exceptions.ResourceNotFoundException;

public interface UserService  extends GenericService<UserDTO> {
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public Map<String, String> loginByUserName(UserLogin credentials) throws ResourceNotFoundException;

}
