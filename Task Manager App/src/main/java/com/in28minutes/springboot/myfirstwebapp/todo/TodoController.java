package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

// Backup controller
//@Controller
@SessionAttributes("name")
public class TodoController {
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	private TodoService todoService;
	
	// /list-todos
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model)	{
		String userName = getLogginUsername(model);
		List<Todo> todos= todoService.findByUsername(userName);
		model.addAttribute("todos", todos);
		return"listTodos";
	}
	
	private String getLogginUsername(ModelMap model) {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model)	{
		// Mapping to form in JSP file, first side of 2 way binding
		String userName = getLogginUsername(model);
		Todo todo = new Todo(0, userName,"", LocalDate.now().plusYears(1), false);
		
		model.put("todo", todo);
		
		return "todo";
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result)	{
		// Don't submit if error occurs
		if(result.hasErrors()) {
			return "todo";
		}
		
		String userName = getLogginUsername(model);
		// Binding directly to bean, second side of 2 way binding
		todoService.addTodo(userName, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id)	{
		// Delete todo with specific id
		todoService.deleteById(id);
		return "redirect:list-todos";
		
	}
	
	@RequestMapping(value ="update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model)	{
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
		
	}
	
	@RequestMapping(value ="update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result)	{
		// Don't submit if error occurs
		if(result.hasErrors()) {
			return "todo";
		}
		
		String userName = getLogginUsername(model);
		todo.setUserName(userName);
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
}
