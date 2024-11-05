package com.levifralex.todo_api_rest;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import com.levifralex.todo_api_rest.dto.TodoDTO;
import com.levifralex.todo_api_rest.exceptions.ResourceNotFoundException;
import com.levifralex.todo_api_rest.service.ServiceException;
import com.levifralex.todo_api_rest.service.TodoService;

@SpringBootTest
class TodoApiRestApplicationTests {

	@Autowired
	private TodoService todoService;

	@Test
	void contextLoads() {
	}

	@SqlGroup({ @Sql({ "/users.sql" }), @Sql({ "/todos.sql" }) })
	@Test
	void testTodoNewUser() throws ResourceNotFoundException, ServiceException {

		Optional<TodoDTO> todo = todoService.findById((long) 200);

		Assertions.assertNotNull(todo.get());

	}

}
