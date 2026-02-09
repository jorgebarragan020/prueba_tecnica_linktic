package com.example.todo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.todo.entity.Task;
import com.example.todo.service.TaskService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	// GET /api/tasks → listar todas las tareas.
	@GetMapping
	public List<Task> getAllTasks() {
		return taskService.findAll();
	}

	// POST /api/tasks → crear una nueva tarea.
	@PostMapping
	public Task createTask(@RequestBody Task task) {
		return taskService.create(task);
	}

	// PUT /api/tasks/{id} → actualizar una tarea existente.
	@PutMapping("/{id}")
	public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
		return taskService.update(id, task);
	}

	// DELETE /api/tasks/{id} → eliminar tarea.
	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable Long id) {
		taskService.delete(id);
	}
}
