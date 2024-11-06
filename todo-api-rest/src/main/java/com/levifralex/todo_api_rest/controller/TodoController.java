package com.levifralex.todo_api_rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levifralex.todo_api_rest.dto.CustomPageable;
import com.levifralex.todo_api_rest.dto.TodoDTO;
import com.levifralex.todo_api_rest.exceptions.ResourceNotFoundException;
import com.levifralex.todo_api_rest.service.ServiceException;
import com.levifralex.todo_api_rest.service.TodoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${api.basicUrl}/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping
	public ResponseEntity<List<TodoDTO>> findAll() throws ServiceException {
		List<TodoDTO> todos = todoService.findAll();
		if (todos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(todos);
	}

	@GetMapping("/{id}")
	public Optional<TodoDTO> findById(@PathVariable Long id) throws ResourceNotFoundException, ServiceException {
		return todoService.findById(id);
	}

	@PostMapping("paging")
	public ResponseEntity<Page<TodoDTO>> findAllPaging(@RequestBody CustomPageable page) throws ServiceException {

		Pageable pageable = PageRequest.of(page.getPageNumber() - 1, page.getPageSize(),
				Sort.by(Direction.valueOf(page.getOrder()), page.getFields()));

		Page<TodoDTO> todos = todoService.findAllPaging(pageable);

		if (todos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(todos);
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody TodoDTO todo) throws ResourceNotFoundException {
		Map<String, Object> body = new HashMap<>();
		try {
			TodoDTO oTodo = todoService.save(todo);
			if (oTodo != null) {
				return new ResponseEntity<>(oTodo, HttpStatus.CREATED);
			}
			body.put("error", "Todo register error");
			return ResponseEntity.badRequest().body(body);
		} catch (ServiceException e) {
			body.put("error", "Internal error");
			return ResponseEntity.internalServerError().body(body);
		}

	}

	@PutMapping("/{id}")
	public TodoDTO update(@PathVariable Long id, @RequestBody @Valid TodoDTO todo) throws ServiceException, ResourceNotFoundException {
		todo.setId(id);
		return todoService.update(todo);
	}

	@PatchMapping("/{id}")
	public void customUpdate(@PathVariable Long id, @RequestBody TodoDTO todo) throws ServiceException, ResourceNotFoundException {
		todo.setId(id);
		todoService.customUpdate(todo);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) throws ServiceException {
		todoService.deleteLogic(id);
	}

}
