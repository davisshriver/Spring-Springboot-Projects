package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

// Manages todos
@Service
public class TodoService {
	private static ArrayList<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;
	
	static	{
		todos.add(new Todo(++todosCount, "DavisShriver","Get AWS Certified 1", 
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "DavisShriver","Learn DevOps 1", 
				LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "DavisShriver","Learn Full Stack Development 1", 
				LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username) {
		// If username matches, convert to stream, if username matches, returns it
		Predicate<? super Todo> predicate = todo -> todo.getUserName().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String userName, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, userName, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		
		// Executes predicate on each todo
		Predicate<? super Todo> predicate 
			= todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		// Defining condition, filter through stream of todos and getting element
		Predicate<? super Todo> predicate 
			= todo -> todo.getId() == id;	
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		
		return todo;
	}
	
	// Delete old value and add new one
	public void updateTodo(Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
