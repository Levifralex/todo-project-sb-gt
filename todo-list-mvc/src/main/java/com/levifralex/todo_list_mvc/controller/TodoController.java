package com.levifralex.todo_list_mvc.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.levifralex.todo_list_mvc.dto.TodoDTO;
import com.levifralex.todo_list_mvc.dto.UserDTO;
import com.levifralex.todo_list_mvc.service.TodoService;
import com.levifralex.todo_list_mvc.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping
public class TodoController {

	@Autowired
	private TodoService todoService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String findAll(TodoDTO todoDTO, Model model) throws ServiceException {
		try {
			List<TodoDTO> todos = todoService.findAll();
			model.addAttribute("todos", todos);
			return "todo-list";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}
	}

	// TODO: REVISAR
	@GetMapping("/nuevo")
	public String nuevo(TodoDTO todoDTO, Model model) {
		try {
			List<UserDTO> users = userService.findAll();
			model.addAttribute("users", users);
			return "todo-save";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	@PostMapping("/grabar")
	public String grabar(TodoDTO todoDTO, Model model) {
		try {

			log.info("todoDTO =>{}", todoDTO);

			if (todoDTO.getId() != null) {
				log.info("Se actualizo");
				todoService.update(todoDTO);
			} else {
				todoService.save(todoDTO);
			}

			List<TodoDTO> listTodoDTO = todoService.findAll();
			model.addAttribute("todos", listTodoDTO);
			return "todo-list";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}
	}

	@GetMapping("/cancelar")
	public String cancelar(TodoDTO todoDTO, Model model) {
		try {
			List<TodoDTO> listTodoDTO = todoService.findAll();
			model.addAttribute("todos", listTodoDTO);
			return "todo-list";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	@GetMapping("/modificar/{id}")
	public String modificar(@PathVariable Long id, Model model) {
		try {
			Optional<TodoDTO> optTodoDTO = todoService.findById(id);
			model.addAttribute("todoDTO", optTodoDTO.get());

			List<UserDTO> users = userService.findAll();
			model.addAttribute("users", users);

			return "todo-save";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(TodoDTO todoDTO, @PathVariable Long id, Model model) {
		try {
			todoService.deleteLogic(id);
			List<TodoDTO> listTodoDTO = todoService.findAll();
			model.addAttribute("todos", listTodoDTO);
			return "todo-list";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}

	}

	@PostMapping("/buscar")
	public String buscar(TodoDTO todoDTO, Model model) {
		try {
			log.info("todoDTO.getDescriptionSearch() " + todoDTO.getDescriptionSearch());

			List<TodoDTO> listTodoDTO = todoService.findByLikeDescription(todoDTO.getDescriptionSearch());

			model.addAttribute("todos", listTodoDTO);

			return "todo-list";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "";
		}
	}

}
