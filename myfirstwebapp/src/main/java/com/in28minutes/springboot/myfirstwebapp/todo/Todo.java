package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

// Store in a database
// Create a list of todos => then use DataBase (H2, MySQL)

@Entity
public class Todo {
	
	public Todo()	{
		
	}
	
	@Id
	@GeneratedValue
	private int id;
	private String username;
	
	@Size(min=10, message="Enter at least 10 characters")
	private String description;
	private LocalDate targetDate;
	private boolean done;
	
	// Constructor
	public Todo(int id, String userName, String description, LocalDate targetDate, boolean done) {
		super();
		this.id = id;
		this.username = userName;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	// To String
	@Override
	public String toString() {
		return "Todo [id=" + id + ", userName=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", done=" + done + "]";
	}
	
	
	
}
