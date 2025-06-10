package com.app.todoapp.repository;

import com.app.todoapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository for Task entities using Spring Data JPA.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Fetch all tasks with the given priority
    List<Task> findByPriority(String priority);  // Works because enum is stored as string
}
