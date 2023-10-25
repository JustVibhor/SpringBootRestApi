package com.in28minutes.rest.webservices.restfulwebservices.todo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "Vibhor","Get AWS Certified",
							LocalDate.now().plusYears(10), false ));
		todos.add(new Todo(++todosCount, "Vibhor","Learn DevOps",
				LocalDate.now().plusYears(11), false ));
		todos.add(new Todo(++todosCount, "Vibhor","Learn Full Stack Development",
				LocalDate.now().plusYears(12), false ));
	}
	
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount,username,description,targetDate,done);
		todos.add(todo);
		return todo;
	}
	
	public void deleteById(int id, String username) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id && todo.getUsername().equals(username);
		todos.removeIf(predicate);
	}

	public Todo findById(int id, String username) {
		List<Todo> listOftodos = findByUsername(username);
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = listOftodos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(Todo todo) {
		deleteById(todo.getId(), todo.getUsername());
		todos.add(todo);
	}
}