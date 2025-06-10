package com.app.todoapp.services;

import com.app.todoapp.models.Priority;
import com.app.todoapp.models.Task;
import com.app.todoapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service layer to handle business logic related to tasks.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Fetches all tasks.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Filters tasks by priority (LOW, MEDIUM, HIGH).
     */
    public List<Task> getTasksByPriority(String priorityStr) {
        return taskRepository.findByPriority(priorityStr.toUpperCase());
    }

    /**
     * Creates a new task with validated priority and due date.
     */
    public void createTask(String title, String priorityStr, LocalDate dueDate) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        task.setDueDate(dueDate);

        try {
            Priority priority = Priority.valueOf(priorityStr.toUpperCase());
            task.setPriority(priority);
        } catch (IllegalArgumentException e) {
            task.setPriority(Priority.MEDIUM);  // Default fallback
        }

        taskRepository.save(task);
    }

    /**
     * Deletes a task by ID.
     */
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Toggles the completed status of a task.
     */
    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task ID: " + id));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
