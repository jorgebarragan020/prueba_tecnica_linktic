package com.example.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	public Task create(Task task) {
		task.setId(null);

		if (task.getCompleted() == null) {
			task.setCompleted(false);
		}

		return taskRepository.save(task);
	}

	public Task update(Long id, Task task) {
		Task existing = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

		existing.setTitle(task.getTitle());
		existing.setDescription(task.getDescription());
		existing.setCompleted(task.getCompleted());

		return taskRepository.save(existing);
	}

	public void delete(Long id) {
		taskRepository.deleteById(id);
	}
}