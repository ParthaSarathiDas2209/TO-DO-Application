package com.app.todoapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private boolean completed;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate dueDate;

    @Transient
    private String friendlyDate;
}
