package com.levifralex.todo_frontend.controller;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.levifralex.todo_frontend.dto.TodoDTO;
import com.levifralex.todo_frontend.dto.UserDTO;
import com.levifralex.todo_frontend.enums.TodoStateEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping
public class TodoController {

	@Value("${custom.uri.back.end}")
	private String uriBase;
	private String uriLocal;

	private RestTemplate restTemplate;

	public TodoController(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.rootUri("").build();
		log.info("uriBase {}", uriBase);
		this.uriLocal = this.uriBase + "/api/v1/todos";
	}

	@GetMapping
	public String findAll(TodoDTO todoDTO, Model model) {
		try {
			this.uriLocal = this.uriBase + "/api/v1/todos";

			ResponseEntity<TodoDTO[]> todosResponse = this.restTemplate.getForEntity(this.uriLocal, TodoDTO[].class);
			log.info("todosResponse {}", todosResponse);

			if (!isNull(todosResponse) && !isNull(todosResponse.getBody())) {
				List<TodoDTO> todos = Arrays.asList(todosResponse.getBody());
				model.addAttribute("todos", todos);
			} else {
				model.addAttribute("todos", new ArrayList<TodoDTO>());
			}

			return "todo-list";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	@GetMapping("/nuevo")
	public String nuevo(TodoDTO todoDTO, Model model) {
		try {

			ResponseEntity<UserDTO[]> usersResponse = this.restTemplate.getForEntity(this.uriBase + "/api/v1/users",
					UserDTO[].class);

			if (!isNull(usersResponse) && !isNull(usersResponse.getBody())) {
				List<UserDTO> users = Arrays.asList(usersResponse.getBody());
				model.addAttribute("users", users);
			} else {
				model.addAttribute("users", new ArrayList<UserDTO>());
			}

			return "todo-save";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	@GetMapping("/cancelar")
	public String cancelar(TodoDTO todoDTO, Model model) {
		try {
			this.uriLocal = this.uriBase + "/api/v1/todos";

			ResponseEntity<TodoDTO[]> todosResponse = this.restTemplate.getForEntity(this.uriLocal, TodoDTO[].class);

			if (!isNull(todosResponse) && !isNull(todosResponse.getBody())) {
				List<TodoDTO> todos = Arrays.asList(todosResponse.getBody());
				model.addAttribute("todos", todos);
			} else {
				model.addAttribute("todos", new ArrayList<TodoDTO>());
			}

			return "todo-list";

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	@PostMapping("/grabar")
	public String grabar(TodoDTO todoDTO, Model model) {
		try {

			log.info("todoDTO {}", todoDTO);

			this.uriLocal = this.uriBase + "/api/v1/todos";
			
			todoDTO.setState(TodoStateEnum.ACTIVE.getCode());

			if (todoDTO.getId() != null) {
				this.restTemplate.put(this.uriLocal + "/" + todoDTO.getId(), todoDTO, TodoDTO.class);
			} else {
				this.restTemplate.postForEntity(this.uriLocal, todoDTO, TodoDTO.class);
			}

			ResponseEntity<TodoDTO[]> todoResponse = this.restTemplate.getForEntity(this.uriLocal, TodoDTO[].class);

			log.info("todoResponse {}", todoResponse);

			if (!isNull(todoResponse) && !isNull(todoResponse.getBody())) {
				List<TodoDTO> empleados = Arrays.asList(todoResponse.getBody());
				model.addAttribute("todos", empleados);
			} else {
				model.addAttribute("todos", new ArrayList<TodoDTO>());
			}
			return "todo-list";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}
	}

	@GetMapping("/modificar/{id}")
	public String modificar(@PathVariable Long id, Model model) {
		try {
			this.uriLocal = this.uriBase + "/api/v1/todos" + "/" + id;
			log.info("uriLocal {}", uriLocal);

			ResponseEntity<UserDTO[]> usersResponse = this.restTemplate.getForEntity(this.uriBase + "/api/v1/users",
					UserDTO[].class);

			if (!isNull(usersResponse) && !isNull(usersResponse.getBody())) {
				List<UserDTO> users = Arrays.asList(usersResponse.getBody());
				model.addAttribute("users", users);
			} else {
				model.addAttribute("users", new ArrayList<UserDTO>());
			}

			ResponseEntity<TodoDTO> todoResponse = this.restTemplate.getForEntity(this.uriLocal, TodoDTO.class);
			if (!isNull(todoResponse) && !isNull(todoResponse.getBody())) {
				model.addAttribute("todoDTO", todoResponse.getBody());
			}

			return "todo-save";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(TodoDTO todoDTO, @PathVariable Long id, Model model) {
		try {
			this.uriLocal = this.uriBase + "/api/v1/todos" + "/" + id;
			log.info("uriLocal {}", uriLocal);

			this.restTemplate.delete(this.uriLocal);

			this.uriLocal = this.uriBase + "/api/v1/todos";
			ResponseEntity<TodoDTO[]> todosResponse = this.restTemplate.getForEntity(this.uriLocal, TodoDTO[].class);
			log.info("todosResponse {}", todosResponse);
			if (!isNull(todosResponse) && !isNull(todosResponse.getBody())) {
				List<TodoDTO> todos = Arrays.asList(todosResponse.getBody());
				model.addAttribute("todos", todos);
			} else {
				model.addAttribute("todos", new ArrayList<TodoDTO>());
			}
			return "todo-list";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	@PostMapping("/buscar")
	public String buscar(TodoDTO todoDTO, Model model) {
		try {

			this.uriLocal = this.uriBase + "/api/v1/todos" + "/find-by-description?description="
					+ todoDTO.getDescriptionSearch();
			log.info("uriLocal {}", uriLocal);

			ResponseEntity<TodoDTO[]> todosResponse = this.restTemplate.getForEntity(this.uriLocal, TodoDTO[].class);
			log.info("todosResponse {}", todosResponse);

			if (!isNull(todosResponse) && !isNull(todosResponse.getBody())) {
				List<TodoDTO> todos = Arrays.asList(todosResponse.getBody());
				model.addAttribute("todos", todos);
			} else {
				model.addAttribute("todos", new ArrayList<TodoDTO>());
			}
			return "todo-list";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}
	}

}
