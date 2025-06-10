package com.app.todoapp.controller;

import com.app.todoapp.models.Task;
import com.app.todoapp.repository.TaskRepository;
import com.app.todoapp.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller for handling all task-related routes in the ToDo Application.
 * Supports CRUD operations, filtering, sorting, flash messages, and validation.
 */
@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Displays the main task list view.
     * Supports optional filtering by priority and sorting by due date.
     *
     * @param priority Optional query param to filter tasks by priority (LOW, MEDIUM, HIGH)
     * @param sort     Optional query param to sort tasks (e.g., "dueDate")
     * @param model    Thymeleaf model to pass attributes to the view
     * @return the main tasks template
     */
    @GetMapping
    public String getTasks(@RequestParam(required = false) String priority,
                           @RequestParam(required = false) String sort,
                           Model model) {
        List<Task> tasks;

        // Filter tasks by priority if provided
        if (priority != null && !priority.isEmpty()) {
            tasks = taskService.getTasksByPriority(priority);
        } else {
            tasks = taskService.getAllTasks();
        }

        // Sort tasks by due date if requested
        if ("dueDate".equals(sort)) {
            tasks.sort((a, b) -> {
                if (a.getDueDate() == null) return 1;
                if (b.getDueDate() == null) return -1;
                return a.getDueDate().compareTo(b.getDueDate());
            });
        }

        // Convert dueDate to friendlyDate string for UI display
        tasks.forEach(task -> task.setFriendlyDate(getFriendlyDate(task.getDueDate())));

        // Add tasks list to model for Thymeleaf rendering
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    /**
     * Handles task creation from form submission.
     * Validates that due date is not in the past.
     *
     * @param title     Task title
     * @param priority  Task priority (LOW, MEDIUM, HIGH)
     * @param dueDate   Task due date
     * @param redirectAttributes For sending flash messages
     * @return Redirects to home page
     */
    @PostMapping
    public String createTask(@RequestParam String title,
                             @RequestParam String priority,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
                             RedirectAttributes redirectAttributes) {

        // Validate due date is not in the past
        if (dueDate.isBefore(LocalDate.now())) {
            redirectAttributes.addFlashAttribute("errorMessage", "⚠️ Due date cannot be in the past.");
            return "redirect:/";
        }

        // Create the new task
        taskService.createTask(title, priority, dueDate);

        // Add success flash message
        redirectAttributes.addFlashAttribute("successMessage", "✅ Task created successfully!");
        return "redirect:/";
    }

    /**
     * Deletes a task by ID.
     *
     * @param id Task ID
     * @param redirectAttributes Flash message
     * @return Redirect to home
     */
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.deleteTask(id);
        redirectAttributes.addFlashAttribute("successMessage", "🗑️ Task deleted.");
        return "redirect:/";
    }

    /**
     * Toggles task completion status.
     *
     * @param id Task ID
     * @param redirectAttributes Flash message
     * @return Redirect to home
     */
    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.toggleTask(id);
        redirectAttributes.addFlashAttribute("successMessage", "🔁 Task status updated.");
        return "redirect:/";
    }

    /**
     * Loads the edit-task form for a specific task.
     *
     * @param id Task ID
     * @param model Thymeleaf model
     * @return View name for edit task page
     */
    @GetMapping("/{id}/edit")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task ID: " + id));
        model.addAttribute("task", task);
        return "edit-task";
    }

    /**
     * Updates a task after editing.
     * Validates due date is not in the past.
     *
     * @param task Updated task object from form
     * @param redirectAttributes Flash message
     * @return Redirect to home or back to edit form on error
     */
    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task, RedirectAttributes redirectAttributes) {
        // Validate due date on update
        if (task.getDueDate() != null && task.getDueDate().isBefore(LocalDate.now())) {
            redirectAttributes.addFlashAttribute("errorMessage", "⚠️ Due date cannot be in the past.");
            return "redirect:/" + task.getId() + "/edit";
        }

        taskRepository.save(task);
        redirectAttributes.addFlashAttribute("successMessage", "✅ Task updated successfully!");
        return "redirect:/";
    }

    /**
     * Converts a due date into a friendly human-readable label (e.g., Today, Tomorrow).
     *
     * @param dueDate LocalDate of the task
     * @return A user-friendly string
     */
    private String getFriendlyDate(LocalDate dueDate) {
        if (dueDate == null) return "";
        LocalDate today = LocalDate.now();
        if (dueDate.equals(today)) return "Today";
        if (dueDate.equals(today.plusDays(1))) return "Tomorrow";
        if (dueDate.isBefore(today)) return "Overdue";
        return dueDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy"));
    }
}
